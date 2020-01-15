package io.crscube.report.report.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by itaesu on 15/01/2020.
 *
 * @author Lee Tae Su
 * @version 2.0
 * @since 2.0
 */
@NoArgsConstructor
@Getter @Setter
public class Product {
    private String name;
    private String title;
    private String reportCount;
    private String studyName;
    private String siteName;
    private String target;
    private String expectedEndDate;
    private String manager;
    private List<String> majors;
}
