import React from "react";
import logo from "./logo.svg";
import "./App.css";
import Hello from "./component/Hello";
import Sourabh from "./component/Sourabh.js"
import Message from "./component/Message"
import Counter from "./component/Counter"
import EventBinding from "./component/EventBinding"
function App() {
  return (
    <div style={{display:'flex',justifyContent:'center'}}><EventBinding/></div>
   // <div  style={{display:'flex',justifyContent:'center'}}><Message/></div>
    //<div  style={{display:'flex',justifyContent:'center'}}><Counter /></div>
    // <div className="App">
    //   <Hello name="Sourabh" heroName="Vin D.">
    //   <p>Hey i am vin Disel</p>
    //   </Hello>
    //   <Hello name="Shubham" heroName="SRK">
    //     <button>Submit</button>
    //   </Hello>
    //   <Sourabh name="Pandit" heroName="Hardik Pandya" />
    //   <Sourabh name="Vishnu" heroName="Paul Walker" />
    // </div>
  );
}

export default App;
