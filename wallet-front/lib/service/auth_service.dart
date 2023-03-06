import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class AuthService {
  static const baseUrl = 'http://192.168.0.103:3333';

  Future<String> login(String email, String password) async {
    const url = '$baseUrl/login';
    final response = await http.post(
      Uri.parse(url),
      body: {'username': email, 'password': password},
    );

    if (response.statusCode == 200) {
      //print(response.body);
      final token = jsonDecode(response.body)['accesse-token'];
      final prefs = await SharedPreferences.getInstance();
      await prefs.setString('jwt_token', token);
      print(token);
      return token;
    } else {
      throw Exception('Failed to login');
    }
  }

}
