const constants = {
     textOnlyRegExp : RegExp(/^[a-zA-Z\s]*$/),
     numOnlyRegExp : RegExp(/^[0-9\b]+$/),
     emailRegExp : RegExp(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/),
     textandDotOnlyRegExp : RegExp(/^([A-Za-z])+(['-.]?[ ]?([A-Za-z]+))+$/),
     nicRegExp : RegExp(/^[0-9]{9}[vVxX]|[0-9]{7}[0][0-9]{4}$/),
     passportRegExp : RegExp(/^[nN][0-9]{8}$/),
     telphoneNumberRegExp :RegExp(/^[0-9]{10}$/),
     addressRegExp:RegExp(/^[a-zA-Z0-9\/\s]*$/),
     adressStreetRegEx:RegExp(/^(\d{3,})\s?(\w{0,5})\s([a-zA-Z]{2,30})\s([a-zA-Z]{2,15})\.?\s?(\w{0,5})$/)
};

module.exports = constants;