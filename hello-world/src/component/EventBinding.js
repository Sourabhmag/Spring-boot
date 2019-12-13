import React, { Component } from "react";

class EventBinding extends Component {
  constructor(props) {
    super(props);

    this.state = {
      message: "hello"
    };
  }
  clickHandler() {
      this.setState({
          message:"Goodbye!"
      })
  }
  render() {
    return (
      <div>
          <h1>message</h1>
        <button onClick={this.clickHandler.bind(this)}>click</button>
      </div>
    );
  }
}

export default EventBinding;
