package com.jia.controller;

import com.jia.model.Account;

import com.jia.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountServiceImpl service;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account a=service.login(account.getUsername(),account.getPassword());
       if( a!=null){              //已经登陆成功
           return new ResponseEntity<Account>(a, HttpStatus.OK);
       }else{
           return  new ResponseEntity<Account>(HttpStatus.CONFLICT);
       }
    }
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public ResponseEntity<Void> reg(@RequestBody Account account) {

        if(service.insert(account)){                  //已经登陆成功
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else{
            return  new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }
    @RequestMapping(value = "/verify/{username}", method = RequestMethod.GET)
    public ResponseEntity<Void> verify(@PathVariable String username) {

        if(service.selectByPrimaryKey(username)==null){                   //已经登陆成功
            return new ResponseEntity<Void>(HttpStatus.OK);
        }else{
            return  new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }
}
