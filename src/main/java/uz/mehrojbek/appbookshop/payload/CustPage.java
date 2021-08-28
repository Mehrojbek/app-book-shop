package uz.mehrojbek.appbookshop.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
@NoArgsConstructor
@Data
public class CustPage<T> {
    private int number;
    private int numberOfElements;
    private int size;
    private Sort sort;
    private int totalPages;
    private long totalElements;
    private Pageable pageable;
    private List<T> content;

    public CustPage(int number, int numberOfElements, int size, Sort sort, int totalPages, long totalElements, Pageable pageable) {
        this.number = number;
        this.numberOfElements = numberOfElements;
        this.size = size;
        this.sort = sort;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageable = pageable;
    }
}
