package com.FinC.services;

import com.FinC.dtos.AccountAndDateDto;
import com.FinC.dtos.AccountDto;
import com.FinC.models.*;
import com.FinC.repositories.AccountRepository;
import com.FinC.repositories.UserRepository;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExpenseService expenseService;

    @Autowired
    RevenueService revenueService;

    @Autowired
    RecurringExpenseService recurringExpenseService;

    public Account findById(UUID id){
        return accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }
    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account createAccount(AccountDto accountDto){
        var account = new Account();
        BeanUtils.copyProperties(accountDto,account);
        var user = userRepository.findById(accountDto.userId()).orElseThrow(()-> new RuntimeException("Cannot be found"));
        account.setUser(user);
        emailService.enviarEmailTexto(account.getUser().getEmail(),
                "Conta registrada - FinC",
                "Parabéns por ter registrado uma conta no FinC " + account.getUser().getName() + ", faça bom proveito. \nEm caso de duvidas nos contate ;) ");
        return accountRepository.save(account);
    }
    public byte[] pdf(AccountAndDateDto accountAndDateDto) {

        List<Expense> expenses = expenseService.findByDate(accountAndDateDto.accountId(),accountAndDateDto.startDate(),accountAndDateDto.endDate());
        List<Revenue> revenues = revenueService.findByDate(accountAndDateDto.accountId(),accountAndDateDto.startDate(),accountAndDateDto.endDate());
        List<RecurringExpense> recurringExpenses = recurringExpenseService.findByDate(accountAndDateDto.accountId(),accountAndDateDto.startDate(),accountAndDateDto.endDate());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph("REVENUES"));
        for(Revenue revenue : revenues){
            var nameP = new Paragraph("Name: " +  revenue.getName());
            var dateP = new Paragraph("Date: " + revenue.getDate());
            var valueP = new Paragraph("Revenue value: " +  revenue.getValue());
            document.add(dateP);
            document.add(valueP);
            document.add(nameP);
            document.add(new Paragraph("         "));
            document.add(new Paragraph("         "));
        }
        document.add(new Paragraph("________________"));

        document.add(new Paragraph("EXPENSES"));
        document.add(new Paragraph("         "));
        for(Expense expense : expenses){
            var nameP = new Paragraph("Name: " +  expense.getName());
            var dateP = new Paragraph("Date: " + expense.getDate());
            var valueP = new Paragraph("Revenue value: " +  expense.getValue());
            document.add(dateP);
            document.add(valueP);
            document.add(nameP);
            document.add(new Paragraph("         "));
            document.add(new Paragraph("         "));
        }
        document.add(new Paragraph("________________"));


        document.add(new Paragraph("RECURRING EXPENSES"));
        for(RecurringExpense recurringExpense : recurringExpenses){
            var nameP = new Paragraph("Name: " +  recurringExpense.getName());
            var dateP = new Paragraph("Date: " + recurringExpense.getDate());
            var valueP = new Paragraph("Revenue value: " +  recurringExpense.getValue());

            document.add(nameP);
            document.add(dateP);
            document.add(valueP);
            document.add(new Paragraph("Frequency: " + recurringExpense.getFrequency()));
            document.add(new Paragraph("         "));
            document.add(new Paragraph("         "));




        }
        document.close();

        return byteArrayOutputStream.toByteArray();

    }
    public Account updateAccount(AccountDto accountDto,UUID id){
        var account = findById(id);
        BeanUtils.copyProperties(accountDto,account);
        return accountRepository.save(account);
    }
    public void deleteAccount(UUID id){
        var account = findById(id);
        accountRepository.delete(account);
    }

}
