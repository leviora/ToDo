//package com.mazowiecka.demo.Service;
//
//import com.mazowiecka.demo.Repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ReportService {
//    private final UserRepository userRepository;
//    public ReportService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    public ReportData generateReport() {
//        long totalUsers = userRepository.count();
//        long totalAdmins = userRepository.countAdmins();
//        long totalRegularUsers = userRepository.countRegularUsers();
//
//        return new ReportData(totalUsers, totalAdmins, totalRegularUsers);
//    }
//    public static class ReportData {
//        private final long totalUsers;
//        private final long totalAdmins;
//        private final long totalRegularUsers;
//
//        public ReportData(long totalUsers, long totalAdmins, long totalRegularUsers) {
//            this.totalUsers = totalUsers;
//            this.totalAdmins = totalAdmins;
//            this.totalRegularUsers = totalRegularUsers;
//        }
//
//        public long getTotalUsers() {
//            return totalUsers;
//        }
//
//        public long getTotalAdmins() {
//            return totalAdmins;
//        }
//
//        public long getTotalRegularUsers() {
//            return totalRegularUsers;
//        }
//    }
//}
