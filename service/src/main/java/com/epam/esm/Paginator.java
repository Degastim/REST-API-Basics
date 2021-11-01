package com.epam.esm;

import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.AbstractEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Paginator<T extends AbstractEntity> {
    public List<T> paginate(List<T> list, PaginationContainer paginationContainer) {
        int page = paginationContainer.getPage();
        int size = paginationContainer.getSize();
        int previousPageEnd = (page - 1) * size;
        Stream<T> stream = list.stream();
        if (page != 0) {
            stream = stream.skip(previousPageEnd).limit(size);
        }
        return stream.collect(Collectors.toList());
    }
}
