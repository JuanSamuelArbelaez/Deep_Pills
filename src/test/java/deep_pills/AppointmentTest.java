package deep_pills;

import deep_pills.dto.appointments.*;
import deep_pills.model.enums.lists.Diagnosis;
import deep_pills.model.enums.lists.Symptom;
import deep_pills.services.interfaces.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class AppointmentTest {
    @Autowired private AppointmentService appointmentService;

    @Test public void scheduleFreeDayForPhysicianTest(){
        try{
            System.out.println("Free day: "+appointmentService.scheduleFreeDayForPhysician("108.479.102", 122L));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test public void serviceAppointmentTest() {
        try {
            ArrayList<AppointmentTreatmentPlanDTO> plans = new ArrayList<>();
            plans.add(new AppointmentTreatmentPlanDTO("Topic cream twice per day on zones", Diagnosis.Acne));
            System.out.println(appointmentService.serviceAppointment(new AppointmentServiceDTO(
                    152L,
                    "723.319.180",
                    "Patient shows signs of sever acne",
                    Arrays.asList(Symptom.Fever, Symptom.Cough),
                    plans
            )));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test public void cancellAppointmentTest() {
        try{
            System.out.println(appointmentService.cancellAppointment(2L));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test public void rescheduleAppointmentTest() {
        try{
            String timeString = "11:30:00";
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date appointmentTime = sdf.parse(timeString);

            System.out.println(appointmentService.rescheduleAppointment(new AppointmentRescheduleDTO(
                    52L,
                    122L,
                    "1012.529.018",
                    "108.479.102",
                    appointmentTime
            )));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test public void scheduleAppointmentTest() {
        try{
            String timeString = "13:30:00";
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date appointmentTime = sdf.parse(timeString);

            Long id = appointmentService.scheduleAppointment(new AppointmentScheduleDTO(
                    "171.018.271",
                    "723.319.180",
                    "Acne",
                    122L,
                    appointmentTime)
            );
            System.out.println("Appointment scheduled: "+id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test public void allAppointmentsByPhysicianIdTest() {
        try{
            List<AppointmentGenericDTO> list = appointmentService.allAppointmentsByPhysicianId("108.479.102");
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
            List<AppointmentGenericDTO> list = appointmentService.upcomingAppointmentsByPhysicianId("108.479.102");
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
            List<AppointmentGenericDTO> list = appointmentService.pastAppointmentsByPhysicianId("108.479.102");
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
        String appointmentDate = "19/10/2023"; // Date to search from
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
        String appointmentDate = "19/10/2023"; // Date to search from
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
