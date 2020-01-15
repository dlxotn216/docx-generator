package io.crscube.report.report.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by itaesu on 15/01/2020.
 *
 * @author Lee Tae Su
 * @version 2.0
 * @since 2.0
 */
@NoArgsConstructor
@Getter @Setter
public class Project {
    private String name;

    public Project(String name) {
        this.name = name;
    }
}
