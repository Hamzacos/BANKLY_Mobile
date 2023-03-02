import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  final String name;
  final String surname;
  final double walletAmount;

  const HomePage({Key key, this.name, this.surname, this.walletAmount})
      : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Home Page'),
        actions: [
          IconButton(
            icon: Icon(Icons.history),
            onPressed: () {
              // Naviguer vers la page d'historique des transactions
            },
          ),
        ],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              'Bienvenue, ${widget.name} ${widget.surname}',
              style: const TextStyle(fontSize: 24),
            ),
            const SizedBox(height: 16),
            Text(
              'Solde de votre portefeuille : ${widget.walletAmount} EUR',
              style: const TextStyle(fontSize: 20),
            ),
            const SizedBox(height: 32),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                IconButton(
                  icon: const Icon(Icons.arrow_downward),
                  onPressed: () {
                    // Débiter le portefeuille
                  },
                ),
                const SizedBox(width: 16),
                IconButton(
                  icon: const Icon(Icons.arrow_upward),
                  onPressed: () {
                    // Créditer le portefeuille
                  },
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
