package com.lbms.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTransactionDTO {
	
	private int transId;
    private String fullName;
    private String email;
    private String mobile;
    private String title;
    private String isbn;
    private Date dueDate;
    private Date issueDate;  
}
