import 'package:flutter/material.dart';

import '../service/transactions_service.dart';

class DebitPage extends StatefulWidget {
  const DebitPage({Key? key}) : super(key: key);

  @override
  _DebitPageState createState() => _DebitPageState();
}

class _DebitPageState extends State<DebitPage> {
  final _formKey = GlobalKey<FormState>();
  final _amountController = TextEditingController();
  final _transactionService = TransactionService();

  @override
  void dispose() {
    _amountController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Debit'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              TextFormField(
                controller: _amountController,
                keyboardType: TextInputType.number,
                decoration: InputDecoration(
                  labelText: 'Amount',
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter an amount';
                  }
                  final doubleAmount = double.tryParse(value);
                  if (doubleAmount == null || doubleAmount <= 0) {
                    return 'Please enter a valid amount';
                  }
                  return null;
                },
              ),
              SizedBox(height: 16),
              ElevatedButton(
                onPressed: () async {
                  if (_formKey.currentState!.validate()) {
                    final doubleAmount = double.parse(_amountController.text);
                    try {
                      await TransactionService.debit(doubleAmount);
                      Navigator.of(context).pop();
                    } catch (e) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(
                          content: Text(e.toString()),
                        ),
                      );
                    }
                  }
                },
                child: Text('Debit'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
