package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks.");
        functionality.add("Provides connection with Trello Account.");
        functionality.add("Application allows sending tasks to Trello.");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8080/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_config", companyConfig);
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyMail(String message) {
        List<String> dailyList = new ArrayList<>();
        dailyList.add("eat");
        dailyList.add("drink");
        dailyList.add("code");
        dailyList.add("sleep");
        dailyList.add("repeat");

        List<String> weekendList = new ArrayList<>();
        weekendList.add("eat");
        weekendList.add("drink");
        weekendList.add("chill");
        weekendList.add("sleep");
        weekendList.add("repeat");

        boolean weekend = LocalDate.now().getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                LocalDate.now().getDayOfWeek().equals(DayOfWeek.SUNDAY);

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("goodbye", "Have a nice day! :)");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("tasks_url", "https://hubertb98.github.io/");
        context.setVariable("weekend", weekend);
        context.setVariable("date_message", "Raport z dnia:" + LocalDate.now());
        context.setVariable("daily_list", dailyList);
        context.setVariable("weekend_list", weekendList);

        return templateEngine.process("mail/daily-mail", context);
    }
}
