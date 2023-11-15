package deep_pills;
import jakarta.mail.internet.MimeMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = "deep_pills")
public class ProyectoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }

    @Component
    public static class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

        private final JavaMailSender javaMailSender;

        public ApplicationStartup(JavaMailSender javaMailSender) {
            this.javaMailSender = javaMailSender;
        }

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            sendEmail("Deep-Pills Running", "Hello there! DeepPills is now up and running!",
                    "juane.astaizaf@uqvirtual.edu.co", "no_reply@deep.pills.com");
            sendEmail("Deep-Pills Running", "Hello there! DeepPills is now up and running!",
                    "deep.pills.org@gmail.com", "no_reply@deep.pills.com");
        }

        private void sendEmail(String subject, String text, String to, String from) {
            ApplicationShutdown.staticSendEmail(subject, text, to, from, javaMailSender);
        }
    }

    @Component
    public static class ApplicationShutdown implements ApplicationListener<ContextClosedEvent> {

        private final JavaMailSender javaMailSender;

        public ApplicationShutdown(JavaMailSender javaMailSender) {
            this.javaMailSender = javaMailSender;
        }

        @Override
        public void onApplicationEvent(ContextClosedEvent event) {
            sendEmail("Deep-Pills Down", "DeepPills is down!.",
                    "juane.astaizaf@uqvirtual.edu.co", "no_reply@deep.pills.com");
            sendEmail("Deep-Pills Down", "DeepPills is down!.",
                    "deep.pills.org@gmail.com", "no_reply@deep.pills.com");
        }

        private void sendEmail(String subject, String text, String to, String from) {
            staticSendEmail(subject, text, to, from, javaMailSender);
        }

        private static void staticSendEmail(String subject, String text, String to, String from, JavaMailSender javaMailSender) {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            try {
                helper = new MimeMessageHelper(message, true);
                helper.setSubject(subject);
                helper.setText(text, true);
                helper.setTo(to);
                helper.setFrom(from);
                javaMailSender.send(message);
            } catch (Exception e) {
                // Manejar excepciones aquí
                e.printStackTrace();
            }
        }
    }
}
