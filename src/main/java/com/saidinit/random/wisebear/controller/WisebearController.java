package com.saidinit.random.wisebear.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saidinit.random.wisebear.domain.Info;
import com.saidinit.random.wisebear.service.WisebearService;

import lombok.Data;

@RestController
@Validated
@Data
@RequestMapping("/wisebear")
public class WisebearController {
	
	WisebearService service;
	
	@GetMapping("/info")
	public Info getInfo() {
		return service.getInfo();
	}

}
