import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:project_bd/Control/Control.dart';
import 'package:project_bd/Model/categories.dart';
import 'package:project_bd/Model/restaurant.dart';

class RestForCategoriesPage extends StatefulWidget {

  Categories _categories;

  RestForCategoriesPage(this._categories);

  @override
  State<StatefulWidget> createState() =>
      _RestForCategoriesPageState( this._categories);
}

class _RestForCategoriesPageState extends State<RestForCategoriesPage> {
  List<Restaurant> _restaurant = new List<Restaurant>();
  Categories _categories;

  _RestForCategoriesPageState( this._categories);

  @override
  void initState() {
    _asyncMethod();
    super.initState();
  }

  _asyncMethod() async{
    Control control = Control.internal();
    control.getCategoriesRest(this._categories.id)
      .then((onValue){
        setState(() {
          this._restaurant = onValue;
        });
      });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: body(),
    );
  }

  Widget body() {
    return Container(
      height: MediaQuery.of(context).size.height,
      width: MediaQuery.of(context).size.width,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Padding(
            padding: EdgeInsets.only(top: 30),
          ),
          Container(
              height: 30,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.start,
                children: <Widget>[
                  FlatButton(
                    child: Icon(
                      Icons.arrow_back_ios,
                      color: Colors.orange,
                    ),
                    onPressed: () {
                      Navigator.pop(context);
                    },
                  ),
                  Padding(
                      padding: EdgeInsets.only(
                          right: MediaQuery.of(context).size.width / 6)),
                  Text(
                    '${this._categories.name}',
                    style: TextStyle(fontSize: 18),
                    textAlign: TextAlign.center,
                  ),
                ],
              )),
          Padding(
            padding: EdgeInsets.only(top: 20),
          ),
          Container(
            height: 10,
            width: MediaQuery.of(context).size.width,
            color: Colors.grey.shade200,
          ),
          Container(
              height: MediaQuery.of(context).size.height - 100,
              margin: EdgeInsets.fromLTRB(10, 0, 10, 0),
              width: MediaQuery.of(context).size.width - 20,
              child: ListView.builder(
                  itemCount: this._restaurant.length,
                  itemBuilder: (BuildContext cntx, int index) {
                    
                    return RawMaterialButton(
                        onPressed: (){goToRest(index);},
                        child: Container(
                            width: MediaQuery.of(context).size.width - 20,
                            height: 80,
                            decoration: BoxDecoration(
                              boxShadow: [
                                BoxShadow(
                                  color: Colors.grey.shade200,
                                  spreadRadius: 1.0, //ext
                                )
                              ],
                              borderRadius: BorderRadius.circular(20),
                              color: Colors.white,
                            ),
                            child: Row(
                              children: <Widget>[
                                Container(
                                    height: 80,
                                    width: 130,
                                    decoration: BoxDecoration(
                                      borderRadius: BorderRadius.only(
                                        topLeft: const Radius.circular(20),
                                      ),
                                    ),
                                    child: ClipRRect(
                                      borderRadius: BorderRadius.only(
                                          topLeft: const Radius.circular(20),
                                          bottomLeft:
                                              const Radius.circular(20)),
                                      child: Image.network(
                                        '${this._restaurant[index].url}',
                                        fit: BoxFit.fill,
                                      ),
                                    )),
                                Container(
                                    height: 130,
                                    margin: EdgeInsets.fromLTRB(15, 0, 10, 0),
                                    child: Center(
                                      child: Text(
                                        '${this._restaurant[index].name}',
                                        style: TextStyle(
                                            fontSize: 15,
                                            fontWeight: FontWeight.w500,
                                            color: Colors.black
                                            ),
                                      ),
                                    ))
                              ],
                            )));
                  }))
        ],
      ),
    );
  }

  void goToRest(int index){
    //vai para o restaurante selecionado
  }


}