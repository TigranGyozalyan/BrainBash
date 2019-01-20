package am.aca.quiz.software.service.mapper.structure;

import java.util.List;


public interface MapEntityToDto<T,U> {
    U mapEntityToDto (T entity);

    List<U> mapEntitiesToDto (List<T> entityList);

}
