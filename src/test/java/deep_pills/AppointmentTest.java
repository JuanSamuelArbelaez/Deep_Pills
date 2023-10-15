package deep_pills;

import deep_pills.dto.appointments.AppointmentGenericDTO;
import deep_pills.services.interfaces.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class AppointmentTest {
    @Autowired private AppointmentService appointmentService;

    @Test public void serviceAppointmentTest() {
        // Implementa tu prueba aquí
        // Llama a appointmentService.serviceAppointment con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }

    @Test public void cancellAppointmentTest() {
        // Implementa tu prueba aquí
        // Llama a appointmentService.cancellAppointment con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }

    @Test public void rescheduleAppointmentTest() {
        // Implementa tu prueba aquí
        // Llama a appointmentService.rescheduleAppointment con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }

    @Test public void scheduleAppointmentTest() {
        // Implementa tu prueba aquí
        // Llama a appointmentService.scheduleAppointment con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }

    @Test public void allAppointmentsByPhysicianIdTest() {
        try{
            List<AppointmentGenericDTO> list = appointmentService.allAppointmentsByPhysicianId("1012.529.018");
            for(AppointmentGenericDTO appointment : list){
                System.out.println(
                        appointment.appointmentId()+" | "+
                                appointment.duration()+" | "+
                                appointment.appointmentState()+" | "+
                                appointment.date()+" | "+
                                appointment.time()+" | "+
                                appointment.personalId());
            }
        }catch (Exception ignored){
        }
    }

    @Test public void upcomingAppointmentsByPhysicianIdTest() {
        try{
            List<AppointmentGenericDTO> list = appointmentService.upcomingAppointmentsByPhysicianId("1012.529.018");
            for(AppointmentGenericDTO appointment : list){
                System.out.println(
                        appointment.appointmentId()+" | "+
                                appointment.duration()+" | "+
                                appointment.appointmentState()+" | "+
                                appointment.date()+" | "+
                                appointment.time()+" | "+
                                appointment.personalId());
            }
        }catch (Exception ignored){
        }
    }

    @Test public void pastAppointmentsByPhysicianIdTest() {
        try{
            List<AppointmentGenericDTO> list = appointmentService.pastAppointmentsByPhysicianId("1012.529.018");
            for(AppointmentGenericDTO appointment : list){
                System.out.println(
                        appointment.appointmentId()+" | "+
                                appointment.duration()+" | "+
                                appointment.appointmentState()+" | "+
                                appointment.date()+" | "+
                                appointment.time()+" | "+
                                appointment.personalId());
            }
        }catch (Exception ignored){
        }
    }

    @Test public void dateSpecificAppointmentsByPhysicianIdTest() {
        String appointmentDate = "18/10/2023"; // Date to search from
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try{
            Date date = formato.parse(appointmentDate);
            List<AppointmentGenericDTO> list = appointmentService.dateSpecificAppointmentsByPhysicianId("108.479.102", date);
            for(AppointmentGenericDTO appointment : list){
                System.out.println(
                        appointment.appointmentId()+" | "+
                                appointment.duration()+" | "+
                                appointment.appointmentState()+" | "+
                                appointment.date()+" | "+
                                appointment.time()+" | "+
                                appointment.personalId());
            }
        }catch (Exception ignored){
        }
    }

    @Test public void allAppointmentsByPatientIdTest() {
        try{
            List<AppointmentGenericDTO> list = appointmentService.allAppointmentsByPatientId("1012.529.018");
            for(AppointmentGenericDTO appointment : list){
                System.out.println(
                        appointment.appointmentId()+" | "+
                                appointment.duration()+" | "+
                                appointment.appointmentState()+" | "+
                                appointment.date()+" | "+
                                appointment.time()+" | "+
                                appointment.personalId());
            }
        }catch (Exception ignored){
        }
    }

    @Test public void upcomingAppointmentsByPatientIdTest() {
        try{
            List<AppointmentGenericDTO> list = appointmentService.upcomingAppointmentsByPatientId("1012.529.018");
            for(AppointmentGenericDTO appointment : list){
                System.out.println(
                        appointment.appointmentId()+" | "+
                                appointment.duration()+" | "+
                                appointment.appointmentState()+" | "+
                                appointment.date()+" | "+
                                appointment.time()+" | "+
                                appointment.personalId());
            }
        }catch (Exception ignored){
        }
    }

    @Test public void pastAppointmentsByPatientIdTest() {
        try{
            List<AppointmentGenericDTO> list = appointmentService.pastAppointmentsByPatientId("1012.529.018");
            for(AppointmentGenericDTO appointment : list){
                System.out.println(
                        appointment.appointmentId()+" | "+
                                appointment.duration()+" | "+
                                appointment.appointmentState()+" | "+
                                appointment.date()+" | "+
                                appointment.time()+" | "+
                                appointment.personalId());
            }
        }catch (Exception ignored){
        }
    }

    @Test public void dateSpecificAppointmentsByPatientIdTest() {
        String appointmentDate = "18/10/2023"; // Date to search from
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try{
            Date date = formato.parse(appointmentDate);
            List<AppointmentGenericDTO> list = appointmentService.dateSpecificAppointmentsByPatientId("1012.529.018", date);
            for(AppointmentGenericDTO appointment : list){
                System.out.println(
                        appointment.appointmentId()+" | "+
                        appointment.duration()+" | "+
                        appointment.appointmentState()+" | "+
                        appointment.date()+" | "+
                        appointment.time()+" | "+
                        appointment.personalId());
            }
        }catch (Exception ignored){
        }
    }
}
