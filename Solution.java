package com.banking;
import java.util.*;
import java.io.*;

class BankingApp {
	private String code;
	private String name;
	private int balance;
	
	BankingApp(String code,String name){
		this.code = code;
		this.name= name;
		this.balance = 0;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setBalance(int newBalance) {
		this.balance = newBalance;
	}
	public String getCode() {
		return this.code;
	}
	public int getBalance() {
		return this.balance;
	}
	public void deposit(int amount) {
		int newAmount = this.balance+amount;
		setBalance(newAmount);
	}
	public void withdraw(int amount) {
		int newAmount = this.balance-amount;
		setBalance(newAmount);
	}
	public static BankingApp search(ArrayList<BankingApp> a,String code) {
		for(BankingApp b:a){
			if(b.getCode().equalsIgnoreCase(code)) {
				return b;
			}
		}
		return new BankingApp("","");
	}
}

public class Solution{
	public static void main(String args[]) {
		
		System.out.println("FOLLOW THE BELOW MANUAL FOR BANKING PROCESS");
		System.out.println("1.To Create Account Enter Command As Below");
		System.out.println("CREATE/create <CODE> <NAME>");
		System.out.println("2.To Deposit Account Enter Command As Below");
		System.out.println("DEPOSIT/deposit <CODE> <AMOUNT>");
		System.out.println("3.To Withdraw Account Enter Command As Below");
		System.out.println("WITHDRAW/withdraw <CODE> <AMOUNT>");
		System.out.println("4.To View Account Balance Enter Command As Below");
		System.out.println("BALANCE/balance <CODE>");
		System.out.println("5.To Exit Enter Command As Below");
		System.out.println("EXIT/exit");
		
		Scanner sc  = new Scanner(System.in);
		ArrayList<BankingApp> a = new ArrayList<>();
		do {
			String input = sc.nextLine();
			String[] input_array = input.split(" ");
			if(input_array[0].equalsIgnoreCase("create")) {
				BankingApp b1 = BankingApp.search(a,input_array[1]);
				if(b1.getName().equals("")) {
					BankingApp b = new BankingApp(input_array[1],input_array[2]);
					a.add(b);
				}
				else {
					System.out.println("Account with same Code exists");
				}
			}
			else if(input_array[0].equalsIgnoreCase("deposit")){
				BankingApp b = BankingApp.search(a,input_array[1]);
				if(!b.getName().equals("")) {
					if(Integer.parseInt(input_array[2])<0) {
						System.out.println("Enter amount greater than 0 to deposit");
					}
					else {
						b.deposit(Integer.parseInt(input_array[2]));
					}
				}
				else {
					System.out.println("Account Not FOund");
				}
			}
			else if(input_array[0].equalsIgnoreCase("withdraw")){
				BankingApp b = BankingApp.search(a,input_array[1]);
				int withdraw_amount = Integer.parseInt(input_array[2]);
				if(!b.getName().equals("")) {
					if(b.getBalance()-withdraw_amount<=0) {
						System.out.println("Not Sufficient Balance");
					}
					else {
						if(Integer.parseInt(input_array[2])<0) {
							System.out.println("Enter amount greater than 0 to withdraw");
						}
						else {
							b.deposit(withdraw_amount);
						}
					}
				}
				else {
					System.out.println("Account Not FOund");
				}
			}
			else if(input_array[0].equalsIgnoreCase("balance")){
				BankingApp b = BankingApp.search(a,input_array[1]);
				if(!b.getName().equals("")) {
					System.out.print(b.getName().toUpperCase()+" ");
					System.out.println(b.getBalance());
				}
				else {
					System.out.println("Account Not FOund");
				}
			}
			else if(input.equalsIgnoreCase("exit")){
				break;
			}
			else {
				System.out.println("Please eneter input in correct format");
				
			}
		}
		while(true);
		sc.close();
	}
}

