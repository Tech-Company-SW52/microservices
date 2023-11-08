package com.client.clientservice.initializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;

import com.client.clientservice.repository.IDepartmentRepository;
import com.client.clientservice.repository.IDistrictRepository;
import com.client.clientservice.repository.IProvinceRepository;

public class RegionInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private IDepartmentRepository notificationRepository;
    @Autowired
    private IProvinceRepository provinceRepository;
    @Autowired
    private IDistrictRepository districtRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (notificationRepository.count() == 0) {
            try {
                Path sqlFilePath = Paths.get("src/main/resources/department.sql");
                String sql = Files.readString(sqlFilePath);
                jdbcTemplate.execute(sql);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (provinceRepository.count() == 0) {
            try {
                Path sqlFilePath = Paths.get("src/main/resources/province.sql");
                String sql = Files.readString(sqlFilePath);
                jdbcTemplate.execute(sql);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (districtRepository.count() == 0) {
            try {
                Path sqlFilePath = Paths.get("src/main/resources/district.sql");
                String sql = Files.readString(sqlFilePath);
                jdbcTemplate.execute(sql);
                sqlFilePath = Paths.get("src/main/resources/district2.sql");
                sql = Files.readString(sqlFilePath);
                jdbcTemplate.execute(sql);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}