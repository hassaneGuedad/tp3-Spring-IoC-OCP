import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import com.example.util.HibernateConfig;

public class TestHibernate {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        System.out.println(sessionFactory != null ? "SessionFactory OK" : "SessionFactory NOK");

        HibernateTransactionManager txManager = context.getBean(HibernateTransactionManager.class);
        System.out.println(txManager != null ? "TransactionManager OK" : "TransactionManager NOK");
    }
}
