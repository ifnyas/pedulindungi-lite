XMLHttpRequest.prototype.oriSend = XMLHttpRequest.prototype.send;

XMLHttpRequest.prototype.send = function(body) {
    this.onreadystatechange = function() {
       if (this.readyState == 4) {
           console.log(this.responseText);
       }
    };
    this.oriSend(body);
};