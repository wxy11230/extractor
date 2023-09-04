
package com.dtb.metadatahub;

import com.dtb.metadatahub.service.ExtractorService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ExtractorApplication
implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(ExtractorApplication.class);
    @Autowired
    private ExtractorService service;

    public static void main(String[] args) {
        SpringApplication.run(ExtractorApplication.class, (String[])args);
    }

    public void run(String ... args) throws Exception {
        if (args.length != 2) {
            log.error("The input parameter length must be 2 ! Check your input parameters");
        } else {
            String collectType = args[0];
            String paramJsonStr = args[1];
            if (StringUtils.isNoneEmpty((CharSequence[])new CharSequence[]{collectType}) && StringUtils.isNoneEmpty((CharSequence[])new CharSequence[]{paramJsonStr})) {
                this.service.getMetadata(collectType, paramJsonStr);
            } else {
                log.error("Neither parameter can be null or empty string");
            }
        }
    }
}
