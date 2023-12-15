package models;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Ticket {

    /* Класс Ticket пакета models реализуем по аналогии c домашним заданием по тестированию API.
       Класс должен содержать набор полей, необходимый для заполнения формы создания тикета.
       Тип данных для каждого поля должен соответствовать документации swagger (см. раздел Models в документации). */

    private Integer id;
    private String due_date;
    private String assigned_to;
    private String title;
    private String created;
    private String modified;
    private String submitter_email;
    private Integer status;
    private Boolean on_hold;
    private String description;
    private String resolution;
    private Integer priority;
    private String last_escalation;
    private String secret_key;
    private Integer queue;
    private Integer kbitem;
    private Integer merged_to;
    private File file;

    public Ticket() {
    }

    public Ticket(Integer id, String due_date, String title, String submitter_email, String description, Integer priority, Integer queue) {
        this.id = id;
        this.due_date = due_date;
        this.title = title;
        this.submitter_email = submitter_email;
        this.description = description;
        this.priority = priority;
        this.queue = queue;
    }

    public Ticket(int id, String due_date, String assigned_to, String title, String created, String modified, String submitter_email, Integer status, Boolean on_hold, String description, String resolution, Integer priority, String last_escalation, String secret_key, Integer queue, Integer kbitem, Integer merged_to, File file) {
        this.id = id;
        this.due_date = due_date;
        this.assigned_to = assigned_to;
        this.title = title;
        this.created = created;
        this.modified = modified;
        this.submitter_email = submitter_email;
        this.status = status;
        this.on_hold = on_hold;
        this.description = description;
        this.resolution = resolution;
        this.priority = priority;
        this.last_escalation = last_escalation;
        this.secret_key = secret_key;
        this.queue = queue;
        this.kbitem = kbitem;
        this.merged_to = merged_to;
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return isOn_hold() == ticket.isOn_hold() && Objects.equals(getId(), ticket.getId()) && Objects.equals(getDue_date(), ticket.getDue_date()) && Objects.equals(getAssigned_to(), ticket.getAssigned_to()) && Objects.equals(getTitle(), ticket.getTitle()) && Objects.equals(getCreated(), ticket.getCreated()) && Objects.equals(getModified(), ticket.getModified()) && Objects.equals(getSubmitter_email(), ticket.getSubmitter_email()) && Objects.equals(getStatus(), ticket.getStatus()) && Objects.equals(getDescription(), ticket.getDescription()) && Objects.equals(getResolution(), ticket.getResolution()) && Objects.equals(getPriority(), ticket.getPriority()) && Objects.equals(getLast_escalation(), ticket.getLast_escalation()) && Objects.equals(getSecret_key(), ticket.getSecret_key()) && Objects.equals(getQueue(), ticket.getQueue()) && Objects.equals(getKbitem(), ticket.getKbitem()) && Objects.equals(getMerged_to(), ticket.getMerged_to()) && Objects.equals(getFile(), ticket.getFile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDue_date(), getAssigned_to(), getTitle(), getCreated(), getModified(), getSubmitter_email(), getStatus(), isOn_hold(), getDescription(), getResolution(), getPriority(), getLast_escalation(), getSecret_key(), getQueue(), getKbitem(), getMerged_to(), getFile());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDue_date() {
        return due_date;
    }

    // обычный сеттер
    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    // перегруженный сеттер, который принимает дату и форматирует её в строку по шаблону
    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getSubmitter_email() {
        return submitter_email;
    }

    public void setSubmitter_email(String submitter_email) {
        this.submitter_email = submitter_email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean isOn_hold() {
        return on_hold;
    }

    public void setOn_hold(Boolean on_hold) {
        this.on_hold = on_hold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getLast_escalation() {
        return last_escalation;
    }

    public void setLast_escalation(String last_escalation) {
        this.last_escalation = last_escalation;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public Integer getQueue() {
        return queue;
    }

    public void setQueue(Integer queue) {
        this.queue = queue;
    }

    public Integer getKbitem() {
        return kbitem;
    }

    public void setKbitem(Integer kbitem) {
        this.kbitem = kbitem;
    }

    public Integer getMerged_to() {
        return merged_to;
    }

    public void setMerged_to(Integer merged_to) {
        this.merged_to = merged_to;
    }
}
