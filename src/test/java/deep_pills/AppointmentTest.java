package deep_pills;

import deep_pills.services.interfaces.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        // Implementa tu prueba aquí
        // Llama a appointmentService.allAppointmentsByPhysicianId con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }

    @Test public void upcomingAppointmentsByPhysicianIdTest() {
        // Implementa tu prueba aquí
        // Llama a appointmentService.upcomingAppointmentsByPhysicianId con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }

    @Test public void pastAppointmentsByPhysicianIdTest() {
        // Implementa tu prueba aquí
        // Llama a appointmentService.pastAppointmentsByPhysicianId con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }

    @Test public void dateSpecificAppointmentsByPhysicianIdTest() {
        // Implementa tu prueba aquí
        // Llama a appointmentService.dateSpecificAppointmentsByPhysicianId con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }

    @Test public void allAppointmentsByPateintIdTest() {
        // Implementa tu prueba aquí
        // Llama a appointmentService.allAppointmentsByPateintId con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }

    @Test public void upcomingAppointmentsByPatientIdTest() {
        // Implementa tu prueba aquí
        // Llama a appointmentService.upcomingAppointmentsByPatientId con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }

    @Test public void pastAppointmentsByPatientIdTest() {
        // Implementa tu prueba aquí
        // Llama a appointmentService.pastAppointmentsByPatientId con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }

    @Test public void dateSpecificAppointmentsByPatientnIdTest() {
        // Implementa tu prueba aquí
        // Llama a appointmentService.dateSpecificAppointmentsByPatientnId con datos de prueba
        // Utiliza assertions para verificar el comportamiento esperado
    }
}
