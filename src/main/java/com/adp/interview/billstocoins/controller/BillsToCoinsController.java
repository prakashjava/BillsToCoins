package com.adp.interview.billstocoins.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.adp.interview.billstocoins.model.BillsToCoinsException;
import com.adp.interview.billstocoins.model.Coins;
import com.adp.interview.billstocoins.service.BillsToCoinsService;

@RestController
public class BillsToCoinsController {

	@Autowired
	private BillsToCoinsService billsToCoinsService;

	@GetMapping("/billstocoins/{dollarAmount}")
	public List<Coins> retrieveCoursesForStudent(@PathVariable int dollarAmount) throws BillsToCoinsException {
			return billsToCoinsService.getCoinsList(dollarAmount);
	}
}
