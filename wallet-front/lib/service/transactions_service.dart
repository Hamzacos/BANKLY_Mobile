import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import '../models/transaction.dart';

class TransactionService {
  static const baseUrl = 'http://192.168.0.103:3333';

  Future<List<Transaction>> getAllTransactions() async {
    final prefs = await SharedPreferences.getInstance();
    final token = prefs.getString('jwt_token');

    final url = '$baseUrl/api/transaction/showAll';

    final response = await http.get(
      Uri.parse(url),
      headers: {'Authorization': 'Bearer $token'},
    );

    if (response.statusCode == 200) {
      final jsonResponse = jsonDecode(response.body) as  List<dynamic>;
      return jsonResponse.map((transactionJson) =>
          Transaction.fromJson(transactionJson)).toList();
    } else {
      throw Exception('Failed to load transactions');
    }
  }


  static Future<void> debit(double amount) async {
    final prefs = await SharedPreferences.getInstance();
    final token = prefs.getString('jwt_token');

    final url = Uri.parse('$baseUrl/api/transaction/debit');

    final body = json.encode({'amount': amount});

    final response = await http.post(
    url,
    headers:{
      'Authorization': 'Bearer $token',
      'Content-Type': 'application/json',
    },
    body: body
    );
    if (response.statusCode != 200) {
      throw Exception('Failed to debit amount: $amount');
    }
  }
}
