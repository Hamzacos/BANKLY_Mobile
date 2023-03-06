import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

import '../models/transaction.dart';
import '../service/transactions_service.dart';

class TransactionHistoryPage extends StatefulWidget {
  const TransactionHistoryPage({Key? key}) : super(key: key);

  @override
  _TransactionHistoryPageState createState() => _TransactionHistoryPageState();
}

class _TransactionHistoryPageState extends State<TransactionHistoryPage> {
  List<Transaction> transactions = [];

  @override
  void initState() {
    super.initState();
    _transactionsFuture();
  }

  Future<List<Transaction>> _transactionsFuture() async {
    try {
      final transactionData = await TransactionService().getAllTransactions();
      setState(() {
        transactions = transactionData;
      });
      return transactionData;
    } catch (e) {
      print(e);
      rethrow;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Transaction History'),
      ),
      body: Center(
        child: FutureBuilder<List<Transaction>>(
          future: _transactionsFuture(),
          builder: (context, snapshot) {
            //if (snapshot.connectionState == ConnectionState.done) {
              if (snapshot.hasData) {
                final transactions = snapshot.data!;
                return ListView.builder(
                  itemCount: transactions.length,
                  itemBuilder: (context, index) {
                    final transaction = transactions[index];
                    return ListTile(
                      title: Text(
                        transaction?.type ?? 'Unknown',
                        style: TextStyle(
                          color: transaction?.type == 'Debiter' ? Colors.red : Colors.green,
                        ),
                      ),
                      subtitle: Text(
                        DateFormat('dd/MM/yyyy').format(transaction?.date ?? DateTime.now()),
                        style: TextStyle(
                          color: Colors.grey,
                        ),
                      ),
                      trailing: Text(
                        '${transaction?.amountTransaction.toString() ?? "N/A"} DH',
                      ),
                    );
                  },
                );
            //  } else {
              //  return const Text('No transactions found');
              //}
            } else {
              return const CircularProgressIndicator();
           }
          },
        ),
      ),
    );
  }
}