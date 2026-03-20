package org.foodie_tour.modules.report.service;

import org.foodie_tour.modules.booking.enums.BookingStatus;
import org.foodie_tour.modules.report.dto.response.BookingReportResponse;
import org.foodie_tour.modules.report.dto.response.ReportResponse;
import org.foodie_tour.modules.report.dto.response.TransactionReportResponse;
import org.foodie_tour.modules.transaction.enums.CashFlow;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    ReportResponse generateReport(LocalDateTime from, LocalDateTime to);
    List<BookingReportResponse> getBookingReport(LocalDateTime from, LocalDateTime to, Integer page, BookingStatus status);
    List<TransactionReportResponse> getTransactionReport(LocalDateTime from, LocalDateTime to, Integer page, CashFlow cashFlow);
}