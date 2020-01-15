XDocReport를 이용한 템플릿 기반 Docx 생성  
https://github.com/opensagres/xdocreport

XDocReport는 내부적으로 freemarker, velocity 등의 템플릿 엔진을 통해 작성 된 템플릿을 기반으로  
docx 문서를 생성할 수 있게 해주는 도구이다. 

사용방법도 매우 간단한데 아래와 같이 템플릿을 준비하고
<img src="https://raw.githubusercontent.com/dlxotn216/docx-generator/master/src/main/resources/static/img01.png" />
 
 
 다음과 같이 준비 된 model을 넣어주면 
```java
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

```

손쉽게 결과를 얻을 수 있다.    
<img src="https://raw.githubusercontent.com/dlxotn216/docx-generator/master/src/main/resources/static/img02.png">