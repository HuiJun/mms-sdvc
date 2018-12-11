package org.openmbee.sdvc.crud.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openmbee.sdvc.json.BaseJson;

public interface BaseIndexDAO {

    Map<String, Object> findByIndexId(String indexId);

    List<Map<String, Object>> findByIndexIds(Set<String> indexIds);

    void indexAll(Collection<? extends BaseJson> jsons);

    void index(BaseJson json);


}