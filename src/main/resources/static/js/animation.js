
var _createClass = function () {function defineProperties(target, props) {for (var i = 0; i < props.length; i++) {var descriptor = props[i];descriptor.enumerable = descriptor.enumerable || false;descriptor.configurable = true;if ("value" in descriptor) descriptor.writable = true;Object.defineProperty(target, descriptor.key, descriptor);}}return function (Constructor, protoProps, staticProps) {if (protoProps) defineProperties(Constructor.prototype, protoProps);if (staticProps) defineProperties(Constructor, staticProps);return Constructor;};}();function _defineProperty(obj, key, value) {if (key in obj) {Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true });} else {obj[key] = value;}return obj;}function _classCallCheck(instance, Constructor) {if (!(instance instanceof Constructor)) {throw new TypeError("Cannot call a class as a function");}}function _possibleConstructorReturn(self, call) {if (!self) {throw new ReferenceError("this hasn't been initialised - super() hasn't been called");}return call && (typeof call === "object" || typeof call === "function") ? call : self;}function _inherits(subClass, superClass) {if (typeof superClass !== "function" && superClass !== null) {throw new TypeError("Super expression must either be null or a function, not " + typeof superClass);}subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } });if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass;} // Silly svg experiment
// The original idea was an animated "presence indicator"
// i.e "Carl is typing..."
// SVG sine loop based on https://codepen.io/jaromvogel/pen/jWjWqN
// Color scheme: https://coolors.co/009ffd-eaf6ff-cacfd6-d6e5e3-9fd8cb

// Character component
var Character = function Character(_ref) {var animation = _ref.animation,armPath = _ref.armPath;
    var characterClass = "character -" + animation;
    return (
        React.createElement("svg", {
                className: characterClass,
                xmlns: "http://www.w3.org/2000/svg",
                viewBox: "0 0 300 400" },

            React.createElement("circle", {
                className: "character__eye -eye-r",
                cx: "87.59",
                cy: "134.46",
                r: "5.12" }),

            React.createElement("g", { id: "body" },
                React.createElement("circle", {
                    className: "character__body -part-1",
                    cx: "140.71",
                    cy: "122.62",
                    r: "42.88" }),

                React.createElement("circle", {
                    className: "character__body -part-2",
                    cx: "166.95",
                    cy: "141.82",
                    r: "42.88" }),

                React.createElement("circle", {
                    className: "character__body -part-3",
                    cx: "191.26",
                    cy: "173.82",
                    r: "42.88" }),

                React.createElement("circle", { className: "character__body", cx: "197.02", cy: "335.1", r: "42.88" }),
                React.createElement("circle", { className: "character__body", cx: "197.02", cy: "295.42", r: "42.88" }),
                React.createElement("circle", { className: "character__body", cx: "206.62", cy: "216.06", r: "42.88" }),
                React.createElement("circle", { className: "character__body", cx: "205.98", cy: "258.94", r: "42.88" })),

            React.createElement("circle", {
                className: "character__eye -eye-l-extra",
                cx: "87.59",
                cy: "134.46",
                r: "5.12" }),

            React.createElement("circle", {
                className: "character__eye -eye-l",
                cx: "115.11",
                cy: "134.46",
                r: "5.12" })));



};

// Left arm component
var ArmLeft = function ArmLeft(_ref2) {var animation = _ref2.animation,armPath = _ref2.armPath;return (
    React.createElement("svg", { className: "arm", xmlns: "http://www.w3.org/2000/svg", viewBox: "0 0 300 400" },
        animation === "typing" && React.createElement("path", { className: "arm-typing-left", d: armPath }),
        animation === "stressed" &&
        React.createElement("path", { className: "arm-typing-left", d: armPath }),

        animation === "waiting" &&
        React.createElement("path", { d: "M175.27,152.06s55.19,87.24-65.77,74.44" }),

        animation === "thinking" &&
        React.createElement("path", { d: "M175.93,152.78s-10.18,82-36.43,103.72" }),

        animation === "passive" &&
        React.createElement("path", { d: "M175.93,152.78s-10.18,82-36.43,103.72" }),

        animation === "sleeping" &&
        React.createElement("path", { d: "M175.93,152.78s-10.18,82-36.43,103.72" })));};




// Right arm component
var ArmRight = function ArmRight(_ref3) {var animation = _ref3.animation,armPath = _ref3.armPath;return (
    React.createElement("svg", { className: "arm", xmlns: "http://www.w3.org/2000/svg", viewBox: "0 0 300 400" },
        animation === "typing" &&
        React.createElement("path", { className: "arm-typing-right", d: armPath }),

        animation === "stressed" &&
        React.createElement("path", { className: "arm-typing-right", d: armPath }),

        animation === "waiting" &&
        React.createElement("path", { d: "M207.26,171.26s45.19,85-75.76,72.24" }),

        animation === "thinking" &&
        React.createElement("path", {
            className: "arm-thinking-right",
            d: "M207.48,172.34s-76,114.16-93-9.84" }),


        animation === "passive" &&
        React.createElement("path", { d: "M207.93,172c.57-.48,11.3,86.45-23.43,112.52" }),

        animation === "sleeping" &&
        React.createElement("path", { d: "M207.93,172c.57-.48,11.3,86.45-23.43,112.52" })));};




// Computer component
var Computer = function Computer(_ref4) {var animation = _ref4.animation;
    var computerClass = "computer -" + animation;
    return (
        React.createElement("svg", {
                className: computerClass,
                xmlns: "http://www.w3.org/2000/svg",
                viewBox: "0 0 82 55" },

            React.createElement("polygon", {
                className: "computer__keyboard",
                points: "29,42.5 81,51.5 45,55.5 30,49.5 " }),

            React.createElement("path", {
                className: "computer__keyboard",
                d: "M80.3,55.5H45.7c-0.9,0-1.7-0.7-1.7-1.7v-0.7c0-0.9,0.7-1.7,1.7-1.7h34.7c0.9,0,1.7,0.7,1.7,1.7v0.7 C82,54.8,81.2,55.5,80.3,55.5z" }),


            React.createElement("path", {
                className: "computer__screen",
                d: "M38.9,55.4l-27.3-6.3c-1.6-0.4-2.8-1.6-3.1-3.2l-8.4-41C-0.5,2.2,1.7-0.2,4.5,0l27.4,2.5 c1.8,0.2,3.3,1.5,3.7,3.3l8.3,44.8C44.4,53.6,41.8,56.1,38.9,55.4z" })));




};

// Table component
var Table = function Table() {return (
    React.createElement("svg", {
            className: "table",
            xmlns: "http://www.w3.org/2000/svg",
            viewBox: "0 0 530 160.1" },

        React.createElement("polygon", { points: "530,65.8 197.7,0 0,10.6 274.9,160.1 " })));};var



    App = function (_React$Component) {_inherits(App, _React$Component);
        function App(props) {_classCallCheck(this, App);var _this = _possibleConstructorReturn(this, (App.__proto__ || Object.getPrototypeOf(App)).call(this,
            props));
            _this.state = {
                animation: "sleeping",
                armPath: "M 207 171",
                frequency: 3,
                amplitude: 0.1,
                xstart: 207,
                ystart: 171,
                length: 110,
                offset: 0,
                fps: 60 };

            _this.createCurve = _this.createCurve.bind(_this);
            _this.setAnimation = _this.setAnimation.bind(_this);
            _this.setConfig = _this.setConfig.bind(_this);
            _this.updateArms = _this.updateArms.bind(_this);
            _this.loop = _this.loop.bind(_this);
            _this.loopref = null;return _this;
        }_createClass(App, [{ key: "createCurve", value: function createCurve(

                x, offset) {var inverted = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : false;var _state =
                this.state,frequency = _state.frequency,ystart = _state.ystart,xstart = _state.xstart,amplitude = _state.amplitude;
                var phase = inverted ?
                    Math.sqrt(x * frequency) - offset :
                    Math.sqrt(x * frequency) + offset;
                return ystart - Math.sin(phase) * (x - xstart) * amplitude;
            } }, { key: "updateArms", value: function updateArms()

            {var _state2 =
                this.state,ystart = _state2.ystart,xstart = _state2.xstart,length = _state2.length;
                var x = xstart;
                var dataL = "M " + xstart + " " + ystart;
                var dataR = "M " + xstart + " " + ystart;

                while (x < xstart + length) {
                    var newYL = this.createCurve(x, this.state.offset);
                    var newYR = this.createCurve(x, this.state.offset, true);
                    dataL = dataL + " L " + x + " " + newYL;
                    dataR = dataR + " L " + x + " " + newYR;
                    x += 1;
                }
                this.setState({
                    armPathL: dataL,
                    armPathR: dataR });

            } }, { key: "loop", value: function loop()

            {var _this2 = this;var _state3 =
                this.state,offset = _state3.offset,animation = _state3.animation,fps = _state3.fps;
                if (animation !== "typing" && animation !== "stressed") {
                    clearTimeout(this.loopRef);
                    return;
                }
                this.setState({
                    offset: offset + 0.3 });

                this.updateArms();
                this.loopRef = setTimeout(function () {
                    requestAnimationFrame(_this2.loop);
                }, 1000 / fps);
            } }, { key: "setAnimation", value: function setAnimation(

                newAnimation, speed) {
                this.setState({
                    animation: newAnimation,
                    fps: speed || 60 });

                if (newAnimation === "typing" || newAnimation === "stressed") {
                    clearTimeout(this.loopRef);
                    requestAnimationFrame(this.loop);
                }
            } }, { key: "setConfig", value: function setConfig(

                e) {
                var type = e.target.name;
                console.log(type);
                this.setState(_defineProperty({},
                    type, e.target.value));

            } }, { key: "render", value: function render()

            {var _this3 = this;var _state4 =
                this.state,frequency = _state4.frequency,amplitude = _state4.amplitude,animation = _state4.animation;
                return (
                    React.createElement("div", { className: "app" },
                        React.createElement("h1", null, "Wormco"),
                        React.createElement("div", { className: "wrapper" },
                            React.createElement(ArmLeft, {
                                animation: this.state.animation,
                                armPath: this.state.armPathL }),

                            React.createElement(Character, { animation: this.state.animation }),
                            React.createElement(ArmRight, {
                                animation: this.state.animation,
                                armPath: this.state.armPathR }),

                            React.createElement(Table, null),
                            React.createElement(Computer, { animation: this.state.animation })),

                        React.createElement("div", { className: "controls" },
                            React.createElement("button", { onClick: function onClick() {return _this3.setAnimation("passive");} }, "Passive"),
                            React.createElement("button", { onClick: function onClick() {return _this3.setAnimation("waiting");} }, "Waiting"),
                            React.createElement("button", { onClick: function onClick() {return _this3.setAnimation("thinking");} }, "Thinking"),


                            React.createElement("button", { onClick: function onClick() {return _this3.setAnimation("typing");} }, "Typing"),
                            React.createElement("button", { onClick: function onClick() {return _this3.setAnimation("stressed", 240);} }, "Nailing it")),



                        animation === "stressed" &&
                        React.createElement("div", { className: "sliders" },
                            React.createElement("input", {
                                type: "range",
                                step: "0.01",
                                name: "frequency",
                                value: frequency,
                                onChange: this.setConfig,
                                min: "0",
                                max: "10" }),

                            React.createElement("input", {
                                type: "range",
                                step: "0.01",
                                name: "amplitude",
                                value: amplitude,
                                onChange: this.setConfig,
                                min: "0.05",
                                max: "2" }))));





            } }]);return App;}(React.Component);


ReactDOM.render(React.createElement(App, null), document.getElementById("root"));var _createClass = function () {function defineProperties(target, props) {for (var i = 0; i < props.length; i++) {var descriptor = props[i];descriptor.enumerable = descriptor.enumerable || false;descriptor.configurable = true;if ("value" in descriptor) descriptor.writable = true;Object.defineProperty(target, descriptor.key, descriptor);}}return function (Constructor, protoProps, staticProps) {if (protoProps) defineProperties(Constructor.prototype, protoProps);if (staticProps) defineProperties(Constructor, staticProps);return Constructor;};}();function _defineProperty(obj, key, value) {if (key in obj) {Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true });} else {obj[key] = value;}return obj;}function _classCallCheck(instance, Constructor) {if (!(instance instanceof Constructor)) {throw new TypeError("Cannot call a class as a function");}}function _possibleConstructorReturn(self, call) {if (!self) {throw new ReferenceError("this hasn't been initialised - super() hasn't been called");}return call && (typeof call === "object" || typeof call === "function") ? call : self;}function _inherits(subClass, superClass) {if (typeof superClass !== "function" && superClass !== null) {throw new TypeError("Super expression must either be null or a function, not " + typeof superClass);}subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } });if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass;} // Silly svg experiment

// Character component
var Character = function Character(_ref) {var animation = _ref.animation,armPath = _ref.armPath;
    var characterClass = "character -" + animation;
    return (
        React.createElement("svg", {
                className: characterClass,
                xmlns: "http://www.w3.org/2000/svg",
                viewBox: "0 0 300 400" },

            React.createElement("circle", {
                className: "character__eye -eye-r",
                cx: "87.59",
                cy: "134.46",
                r: "5.12" }),

            React.createElement("g", { id: "body" },
                React.createElement("circle", {
                    className: "character__body -part-1",
                    cx: "140.71",
                    cy: "122.62",
                    r: "42.88" }),

                React.createElement("circle", {
                    className: "character__body -part-2",
                    cx: "166.95",
                    cy: "141.82",
                    r: "42.88" }),

                React.createElement("circle", {
                    className: "character__body -part-3",
                    cx: "191.26",
                    cy: "173.82",
                    r: "42.88" }),

                React.createElement("circle", { className: "character__body", cx: "197.02", cy: "335.1", r: "42.88" }),
                React.createElement("circle", { className: "character__body", cx: "197.02", cy: "295.42", r: "42.88" }),
                React.createElement("circle", { className: "character__body", cx: "206.62", cy: "216.06", r: "42.88" }),
                React.createElement("circle", { className: "character__body", cx: "205.98", cy: "258.94", r: "42.88" })),

            React.createElement("circle", {
                className: "character__eye -eye-l-extra",
                cx: "87.59",
                cy: "134.46",
                r: "5.12" }),

            React.createElement("circle", {
                className: "character__eye -eye-l",
                cx: "115.11",
                cy: "134.46",
                r: "5.12" })));



};

// Left arm component
var ArmLeft = function ArmLeft(_ref2) {var animation = _ref2.animation,armPath = _ref2.armPath;return (
    React.createElement("svg", { className: "arm", xmlns: "http://www.w3.org/2000/svg", viewBox: "0 0 300 400" },
        animation === "typing" && React.createElement("path", { className: "arm-typing-left", d: armPath }),
        animation === "stressed" &&
        React.createElement("path", { className: "arm-typing-left", d: armPath }),

        animation === "waiting" &&
        React.createElement("path", { d: "M175.27,152.06s55.19,87.24-65.77,74.44" }),

        animation === "thinking" &&
        React.createElement("path", { d: "M175.93,152.78s-10.18,82-36.43,103.72" }),

        animation === "passive" &&
        React.createElement("path", { d: "M175.93,152.78s-10.18,82-36.43,103.72" }),

        animation === "sleeping" &&
        React.createElement("path", { d: "M175.93,152.78s-10.18,82-36.43,103.72" })));};




// Right arm component
var ArmRight = function ArmRight(_ref3) {var animation = _ref3.animation,armPath = _ref3.armPath;return (
    React.createElement("svg", { className: "arm", xmlns: "http://www.w3.org/2000/svg", viewBox: "0 0 300 400" },
        animation === "typing" &&
        React.createElement("path", { className: "arm-typing-right", d: armPath }),

        animation === "stressed" &&
        React.createElement("path", { className: "arm-typing-right", d: armPath }),

        animation === "waiting" &&
        React.createElement("path", { d: "M207.26,171.26s45.19,85-75.76,72.24" }),

        animation === "thinking" &&
        React.createElement("path", {
            className: "arm-thinking-right",
            d: "M207.48,172.34s-76,114.16-93-9.84" }),


        animation === "passive" &&
        React.createElement("path", { d: "M207.93,172c.57-.48,11.3,86.45-23.43,112.52" }),

        animation === "sleeping" &&
        React.createElement("path", { d: "M207.93,172c.57-.48,11.3,86.45-23.43,112.52" })));};




// Computer component
var Computer = function Computer(_ref4) {var animation = _ref4.animation;
    var computerClass = "computer -" + animation;
    return (
        React.createElement("svg", {
                className: computerClass,
                xmlns: "http://www.w3.org/2000/svg",
                viewBox: "0 0 82 55" },

            React.createElement("polygon", {
                className: "computer__keyboard",
                points: "29,42.5 81,51.5 45,55.5 30,49.5 " }),

            React.createElement("path", {
                className: "computer__keyboard",
                d: "M80.3,55.5H45.7c-0.9,0-1.7-0.7-1.7-1.7v-0.7c0-0.9,0.7-1.7,1.7-1.7h34.7c0.9,0,1.7,0.7,1.7,1.7v0.7 C82,54.8,81.2,55.5,80.3,55.5z" }),


            React.createElement("path", {
                className: "computer__screen",
                d: "M38.9,55.4l-27.3-6.3c-1.6-0.4-2.8-1.6-3.1-3.2l-8.4-41C-0.5,2.2,1.7-0.2,4.5,0l27.4,2.5 c1.8,0.2,3.3,1.5,3.7,3.3l8.3,44.8C44.4,53.6,41.8,56.1,38.9,55.4z" })));




};

// Table component
var Table = function Table() {return (
    React.createElement("svg", {
            className: "table",
            xmlns: "http://www.w3.org/2000/svg",
            viewBox: "0 0 530 160.1" },

        React.createElement("polygon", { points: "530,65.8 197.7,0 0,10.6 274.9,160.1 " })));};var



    App = function (_React$Component) {_inherits(App, _React$Component);
        function App(props) {_classCallCheck(this, App);var _this = _possibleConstructorReturn(this, (App.__proto__ || Object.getPrototypeOf(App)).call(this,
            props));
            _this.state = {
                animation: "sleeping",
                armPath: "M 207 171",
                frequency: 3,
                amplitude: 0.1,
                xstart: 207,
                ystart: 171,
                length: 110,
                offset: 0,
                fps: 60 };

            _this.createCurve = _this.createCurve.bind(_this);
            _this.setAnimation = _this.setAnimation.bind(_this);
            _this.setConfig = _this.setConfig.bind(_this);
            _this.updateArms = _this.updateArms.bind(_this);
            _this.loop = _this.loop.bind(_this);
            _this.loopref = null;return _this;
        }_createClass(App, [{ key: "createCurve", value: function createCurve(

                x, offset) {var inverted = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : false;var _state =
                this.state,frequency = _state.frequency,ystart = _state.ystart,xstart = _state.xstart,amplitude = _state.amplitude;
                var phase = inverted ?
                    Math.sqrt(x * frequency) - offset :
                    Math.sqrt(x * frequency) + offset;
                return ystart - Math.sin(phase) * (x - xstart) * amplitude;
            } }, { key: "updateArms", value: function updateArms()

            {var _state2 =
                this.state,ystart = _state2.ystart,xstart = _state2.xstart,length = _state2.length;
                var x = xstart;
                var dataL = "M " + xstart + " " + ystart;
                var dataR = "M " + xstart + " " + ystart;

                while (x < xstart + length) {
                    var newYL = this.createCurve(x, this.state.offset);
                    var newYR = this.createCurve(x, this.state.offset, true);
                    dataL = dataL + " L " + x + " " + newYL;
                    dataR = dataR + " L " + x + " " + newYR;
                    x += 1;
                }
                this.setState({
                    armPathL: dataL,
                    armPathR: dataR });

            } }, { key: "loop", value: function loop()

            {var _this2 = this;var _state3 =
                this.state,offset = _state3.offset,animation = _state3.animation,fps = _state3.fps;
                if (animation !== "typing" && animation !== "stressed") {
                    clearTimeout(this.loopRef);
                    return;
                }
                this.setState({
                    offset: offset + 0.3 });

                this.updateArms();
                this.loopRef = setTimeout(function () {
                    requestAnimationFrame(_this2.loop);
                }, 1000 / fps);
            } }, { key: "setAnimation", value: function setAnimation(

                newAnimation, speed) {
                this.setState({
                    animation: newAnimation,
                    fps: speed || 60 });

                if (newAnimation === "typing" || newAnimation === "stressed") {
                    clearTimeout(this.loopRef);
                    requestAnimationFrame(this.loop);
                }
            } }, { key: "setConfig", value: function setConfig(

                e) {
                var type = e.target.name;
                console.log(type);
                this.setState(_defineProperty({},
                    type, e.target.value));

            } }, { key: "render", value: function render()

            {var _this3 = this;var _state4 =
                this.state,frequency = _state4.frequency,amplitude = _state4.amplitude,animation = _state4.animation;
                return (
                    React.createElement("div", { className: "app" },
                        React.createElement("h1", null, "Wormco"),
                        React.createElement("div", { className: "wrapper" },
                            React.createElement(ArmLeft, {
                                animation: this.state.animation,
                                armPath: this.state.armPathL }),

                            React.createElement(Character, { animation: this.state.animation }),
                            React.createElement(ArmRight, {
                                animation: this.state.animation,
                                armPath: this.state.armPathR }),

                            React.createElement(Table, null),
                            React.createElement(Computer, { animation: this.state.animation })),

                        React.createElement("div", { className: "controls" },
                            React.createElement("button", { onClick: function onClick() {return _this3.setAnimation("passive");} }, "Passive"),
                            React.createElement("button", { onClick: function onClick() {return _this3.setAnimation("waiting");} }, "Waiting"),
                            React.createElement("button", { onClick: function onClick() {return _this3.setAnimation("thinking");} }, "Thinking"),


                            React.createElement("button", { onClick: function onClick() {return _this3.setAnimation("typing");} }, "Typing"),
                            React.createElement("button", { onClick: function onClick() {return _this3.setAnimation("stressed", 240);} }, "Nailing it")),



                        animation === "stressed" &&
                        React.createElement("div", { className: "sliders" },
                            React.createElement("input", {
                                type: "range",
                                step: "0.01",
                                name: "frequency",
                                value: frequency,
                                onChange: this.setConfig,
                                min: "0",
                                max: "10" }),

                            React.createElement("input", {
                                type: "range",
                                step: "0.01",
                                name: "amplitude",
                                value: amplitude,
                                onChange: this.setConfig,
                                min: "0.05",
                                max: "2" }))));





            } }]);return App;}(React.Component);


ReactDOM.render(React.createElement(App, null), document.getElementById("root"));
