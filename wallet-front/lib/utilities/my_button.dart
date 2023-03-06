import 'package:flutter/material.dart';

class MyButton extends StatelessWidget {
  final String iconImagePath;
  final String buttonText;
  final Function onTap;

  const MyButton({
    Key? key,
    required this.iconImagePath,
    required this.buttonText,
    required this.onTap,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Navigator.pushNamed(context, 'DebitPage');
      },
      child: Column(
        children: [
          Container(
            height: 90,
            padding: EdgeInsets.all(20),
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.circular(20),
              boxShadow: [
                BoxShadow(
                  color: Colors.grey.shade600,
                  blurRadius: 19,
                  spreadRadius: 2,
                )
              ],
            ),
            child: Center(
              child: Image.asset(iconImagePath),
            ),
          ),
          SizedBox(
            height: 10,
          ),
          Text(
            buttonText,
            style: TextStyle(
              fontSize: 18,
              fontWeight: FontWeight.bold,
            ),
          ),
        ],
      ),
    );
  }
}
