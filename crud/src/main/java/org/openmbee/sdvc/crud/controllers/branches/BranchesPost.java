package org.openmbee.sdvc.crud.controllers.branches;

import java.time.Instant;
import org.openmbee.sdvc.crud.config.DbContextHolder;
import org.openmbee.sdvc.crud.controllers.BaseController;
import org.openmbee.sdvc.crud.controllers.BaseResponse;
import org.openmbee.sdvc.crud.controllers.ErrorResponse;
import org.openmbee.sdvc.crud.domains.Branch;
import org.openmbee.sdvc.crud.domains.Commit;
import org.openmbee.sdvc.crud.repositories.branch.BranchDAO;
import org.openmbee.sdvc.crud.repositories.commit.CommitDAO;
import org.openmbee.sdvc.crud.services.DatabaseDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/{projectId}/refs")
public class BranchesPost extends BaseController {

    private BranchDAO branchRepository;

    private DatabaseDefinitionService branchesOperations;

    private CommitDAO commitRepository;

    @Autowired
    public BranchesPost(BranchDAO branchRepository, DatabaseDefinitionService branchesOperations,
        CommitDAO commitRepository) {
        this.branchRepository = branchRepository;
        this.branchesOperations = branchesOperations;
        this.commitRepository = commitRepository;
    }

    @PostMapping
    public ResponseEntity<? extends BaseResponse> handleRequest(
        @PathVariable String projectId,
        @RequestBody BranchesRequest projectsPost) {
        if (!projectsPost.getRefs().isEmpty()) {
            logger.info("JSON parsed properly");
            BranchesResponse response = new BranchesResponse();
            Instant now = Instant.now();

            for (RefJson branch : projectsPost.getRefs()) {
                DbContextHolder.setContext(projectId);
                Branch b = new Branch();
                b.setBranchId(branch.getId());
                b.setBranchName(branch.getName());
                b.setElasticId("blah");
                b.setTimestamp(Instant.now());
                logger.info("Saving branch: {}", branch.getId());

                if (branch.getParentRefId() != null) {
                    Branch parentRef = branchRepository.findByBranchId(branch.getParentRefId());
                    b.setParentRef(parentRef);
                } else {
                    b.setParentRef(branchRepository.findByBranchId("master"));
                }

                if (branch.getParentCommitId() != null) {
                    Commit parentCommit = commitRepository
                        .findByCommitId(branch.getParentCommitId());
                    b.setParentCommit(parentCommit);
                } else {
                    b.setParentCommit(commitRepository.findLatest());
                }

                b.setTimestamp(now);

                Branch saved = branchRepository.save(b);
                try {
                    DbContextHolder.setContext(projectId, saved.getBranchId());
                    if (branchesOperations.createBranch()) {
                        branchesOperations.copyTablesFromParent(saved.getBranchId(),
                            b.getParentRef().getBranchId(), branch.getParentCommitId());
                    }
                    response.getBranches().add(branch);
                } catch (Exception e) {
                    branchRepository.delete(saved);
                    logger.error("Couldn't create branch: {}", branch.getId());
                    logger.error(e);
                }

            }

            return ResponseEntity.ok(response);
        }
        logger.debug("Bad Request");
        ErrorResponse err = new ErrorResponse();
        err.setCode(400);
        err.setError("Bad Request");
        return ResponseEntity.badRequest().body(err);
    }
}
