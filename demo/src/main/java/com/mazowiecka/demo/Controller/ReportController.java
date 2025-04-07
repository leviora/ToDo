//package com.mazowiecka.demo.Controller;
//
//import com.mazowiecka.demo.Service.ReportService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/dashboard")
//
//public class ReportController {
//
//    private final ReportService reportService;
//
//    public ReportController(ReportService reportService) {
//        this.reportService = reportService;
//    }
//
//    @GetMapping()
//    public String showDashboard() {
//        return "pages/mainAdminDashboard";
//    }
//
//    @GetMapping("/reports")
//    public String showReports(Model model) {
//        ReportService.ReportData reportData = reportService.generateReport();
//
//        model.addAttribute("totalUsers", reportData.getTotalUsers());
//        model.addAttribute("admins", reportData.getTotalAdmins());
//        model.addAttribute("regularUsers", reportData.getTotalRegularUsers());
//
//        return "pages/report";
//    }
//}
