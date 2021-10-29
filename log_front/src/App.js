import React from 'react';
import './App.css';
import axios from 'axios';

class LogApp extends React.Component {
  constructor(props) {
    super(props);
    this.state = { items: [], text: '', server: '' };
    this.server = process.env.BALANCER_HOST || 'http://ec2-54-88-223-161.compute-1.amazonaws.com:8090';
    console.log(process.env)
    axios.get(this.server + '/latest').then(value => this.setState(state => ({
      items: value.data,
      text: ''
    }))).catch(reason => console.log(reason));
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  render() {
    return (
        <div>
          <h3>Logs</h3>
          <form onSubmit={this.handleSubmit}>
            <label htmlFor="new-log">
              ¿Qué quiere loggear?
            </label>
            <input
                id="new-log"
                onChange={this.handleChange}
                value={this.state.text}
            />
            <button>
              Añadir
            </button>
          </form>
          <LogList items={this.state.items} />
        </div>
    );
  }

  handleChange(e) {
    this.setState({ text: e.target.value });
  }

  async handleSubmit(e) {
    e.preventDefault();
    if (this.state.text.length === 0) {
      return;
    }
    await axios.get(this.server + '/save',
        {params: {text: this.state.text}}
    ).then(value => this.setState(state => ({
      items: value.data,
      text: ''
    }))).catch(reason => console.log(reason))
  }
}

class LogList extends React.Component {
  render() {
    return (
        <table>
          <tr>
            <td>Mensaje</td>
            <td>Fecha</td>
          </tr>
          {this.props.items.map(item => (
              <tr>
                <td>{item.text}</td>
                <td>{item.date}</td>
              </tr>
          ))}
        </table>
    );
  }
}

export default LogApp;
