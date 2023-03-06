import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:http/http.dart' as http;

import '../models/wallet.dart';

class WalletService{

  Future<Wallet> getWallet() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token;

    try {
      token = prefs.getString('jwt_token');
    } catch (Exception) {
      print('Error retrieving token from SharedPreferences');
    }

    final response = await http.get(
        Uri.parse('http://192.168.0.103:3333/api/wallet/mywallets'),
        headers: {'Authorization': 'Bearer $token'});

    if (response.statusCode == 200) {
      final jsonResponse = jsonDecode(response.body) as List<dynamic>;
      final wallets = jsonResponse.map((walletJson) => Wallet.fromJson(walletJson)).toList();
      return wallets.first;
    } else {
      throw Exception('Failed to load wallet');
    }
  }

  static Future<void> logout() async {
    final prefs = await SharedPreferences.getInstance();
    final token = prefs.getString('jwt_token');

    final url = Uri.parse('$baseUrl/api/auth/logout');

    final response = await http.post(
      url,
      headers: {'Authorization': 'Bearer $token'},
    );

    if (response.statusCode == 200) {
      // Logout successful, clear user data from shared preferences
      prefs.remove('jwt_token');
      prefs.remove('user_id');
    } else {
      throw Exception('Failed to logout');
    }
  }

}
