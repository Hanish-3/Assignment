    package com.example.billing_service.controller;

    import com.example.billing_service.model.Invoice;
    import com.example.billing_service.service.BillingService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/billing")
    public class BillingController {
        @Autowired
        private BillingService billingService;

        @PostMapping
        public ResponseEntity<Invoice> createInvoice(
                @RequestParam Long patientId,
                @RequestParam Long appointmentId,
                @RequestParam Double amount) {
            return ResponseEntity.status(HttpStatus.CREATED).body(billingService.createInvoice(patientId, appointmentId, amount));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
            return billingService.getInvoiceById(id)
                    .map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @GetMapping("/patient/{patientId}")
        public List<Invoice> getInvoicesByPatientId(@PathVariable Long patientId) {
            return billingService.getInvoicesByPatientId(patientId);
        }

        @GetMapping
        public List<Invoice> getAllInvoices() {
            return billingService.getAllInvoices();
        }

        @PutMapping("/{id}/status")
        public ResponseEntity<Invoice> updateInvoiceStatus(@PathVariable Long id, @RequestParam String status) {
            try {
                Invoice updatedInvoice = billingService.updateInvoiceStatus(id, status);
                return ResponseEntity.ok(updatedInvoice);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
    }
    