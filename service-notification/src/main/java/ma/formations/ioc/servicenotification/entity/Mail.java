package ma.formations.ioc.servicenotification.entity;


import org.springframework.beans.factory.annotation.Value;

public class Mail {

    @Value("minoxrofix@gmail.com")
    private String to;
    @Value("Test")
    private String subject;
    @Value("Test")
    private String body;
    @Value("${spring.mail.username}")
    private String from;

    public Mail() {
    }

    public Mail(String to, String subject, String body) {

        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

}
