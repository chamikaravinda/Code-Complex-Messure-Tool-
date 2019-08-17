const lengthCalculator = {
    lengthWithoutSpaces(stringValue){
        let remText = stringValue.replace(/\s/g, "");
        return remText.length;
    }
}

export { lengthCalculator as default } 