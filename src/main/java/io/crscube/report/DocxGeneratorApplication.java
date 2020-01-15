package io.crscube.report;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import io.crscube.report.report.domain.Document;
import io.crscube.report.report.domain.Product;
import io.crscube.report.report.domain.Project;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

@SpringBootApplication
public class DocxGeneratorApplication {

    public static void main(String[] args) {
        //        SpringApplication.run(DocxGeneratorApplication.class, args);
        try {
            // 1) Load Docx file by filling Velocity template engine and cache it to the registry
            InputStream in = new ClassPathResource("Example.docx").getInputStream();
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

            // 2) Create context Java model
            IContext context = report.createContext();
            Project project = new Project("XDocReport");
            context.put("project", project);

            Document document = new Document();
            document.setTitle("4. 연구자 주도 임상");
            context.put("document", document);
            context.put("no", 1);

            Product product = new Product();
            product.setName("듀비에");
            product.setReportCount("7");
            product.setTitle("듀비에 (7건)");
            product.setStudyName("듀비에(인슐린)");
            product.setSiteName("부산백병원(다기관");
            product.setTarget("13/98");
            product.setExpectedEndDate("2020-12-04");
            product.setManager("MSL팀");
            product.setMajors(
                    Arrays.asList(
                            "허가 후 임상시험 계획 제안 협조문 수령(2016.04.06)",
                            "허가 후 임상시험 제안회의(2016.04.12) -> 회의록 송부(2016.04.25)"
                    )
            );
            context.put("product", product);

            // 3) Generate report by merging Java model with the Docx
            OutputStream out = new FileOutputStream(new File("result.docx"));

            report.process(context, out);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XDocReportException e) {
            e.printStackTrace();
        }
    }

}
