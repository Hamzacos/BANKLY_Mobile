import 'dart:ui';

import 'package:flutter/material.dart';


class Transaction {
  final double amountTransaction;
  final DateTime date;
  final String type;

  Transaction({required this.amountTransaction, required this.date, required this.type});

  factory Transaction.fromJson(Map<String, dynamic> json) {
    //final bool isDebit = json['type'] == 'Debiter';
    //final Color textColor = isDebit ? Colors.red : Colors.green;

    return Transaction(
      amountTransaction: json['amountTransaction'] ?? 0.0, // check if 'amount' is null and assign 0.0 if it is
      type: json['type'] ?? '', // check if 'type' is null and assign an empty string if it is
      date: json['date'] != null ? DateTime.parse(json['date']) : DateTime.now(), // check if 'date' is null and assign the current time if it is
      //textColor: textColor,
    );
  }

  get textColor => null;
}
