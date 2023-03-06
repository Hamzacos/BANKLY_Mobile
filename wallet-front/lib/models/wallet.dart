import 'package:flutter/material.dart';


class Wallet {
  final int id;
  final double balance;
  final String currency;

  Wallet({
    required this.id,
    required this.balance,
    required this.currency,
  });

  factory Wallet.fromJson(Map<String, dynamic> json) {
    return Wallet(
      id: json['id'],
      balance: json['balance'],
      currency: json['currency'],
    );
  }
}