package business.logic.lab2.quarts;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RemindJob implements Job {

    @Autowired
    private ReminderService reminderService;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        reminderService.putRemindsInQueue();
    }
}


