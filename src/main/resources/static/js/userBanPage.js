function hideClass(name) {
    for (let elem of $(name)) elem.style.display = "none";
}

let DEFAULT_WIDTH = 600, FPS = 60;
let IS_HIDPI = false, IS_IOS = false, IS_MOBILE = false, IS_TOUCH_ENABLED = false;

function Runner(outerContainerId) {
    Runner.instance_ = this;
    let outerContainerEl = document.querySelector(outerContainerId);
    this.outerContainerEl = outerContainerEl
    this.containerEl = null;
    this.detailsButton = outerContainerEl.querySelector("#details-button");
    this.config = Runner.config;
    this.dimensions = Runner.defaultDimensions;
    this.canvas = null;
    this.canvasCtx = null;
    this.tRex = null;
    this.distanceMeter = null;
    this.distanceRan = 0;
    this.highestScore = 0;
    this.time = 0;
    this.runningTime = 0;
    this.msPerFrame = 1000 / FPS;
    this.currentSpeed = Runner.config.SPEED;
    this.obstacles = [];
    this.started = false;
    this.activated = false;
    this.crashed = false;
    this.paused = false;
    this.resizeTimerId_ = null;
    this.playCount = 0;
    this.audioBuffer = null;
    this.soundFx = {};
    this.audioContext = null;
    this.images = {};
    this.imagesLoaded = 0;
    loadImages(this);
};
Runner.config = {
    ACCELERATION: 0.001,
    BG_CLOUD_SPEED: 0.2,
    BOTTOM_PAD: 10,
    CLEAR_TIME: 3000,
    CLOUD_FREQUENCY: 0.5,
    GAMEOVER_CLEAR_TIME: 750,
    GAP_COEFFICIENT: 0.6,
    GRAVITY: 0.6,
    INITIAL_JUMP_VELOCITY: 12,
    MAX_CLOUDS: 6,
    MAX_OBSTACLE_LENGTH: 3,
    MAX_SPEED: 12,
    MIN_JUMP_HEIGHT: 35,
    MOBILE_SPEED_COEFFICIENT: 1.2,
    RESOURCE_TEMPLATE_ID: "audio-resources",
    SPEED: 6,
    SPEED_DROP_COEFFICIENT: 3
};
Runner.defaultDimensions = {
    WIDTH: DEFAULT_WIDTH,
    HEIGHT: 150
};
Runner.classes = {
    CANVAS: "runner-canvas",
    CONTAINER: "runner-container",
    CRASHED: "crashed",
    ICON: "icon-offline",
    TOUCH_CONTROLLER: "controller"
};
Runner.imageSources = {
    LDPI: [
        {name: "CACTUS_LARGE", id: "1x-obstacle-large"},
        {name: "CACTUS_SMALL", id: "1x-obstacle-small"},
        {name: "CLOUD", id: "1x-cloud"},
        {name: "HORIZON", id: "1x-horizon"},
        {name: "RESTART", id: "1x-restart"},
        {name: "TEXT_SPRITE", id: "1x-text"},
        {name: "TREX", id: "1x-trex"}
    ]/*,
                HDPI: [
                    { name: "CACTUS_LARGE", id: "2x-obstacle-large" },
                    { name: "CACTUS_SMALL", id: "2x-obstacle-small" },
                    { name: "CLOUD", id: "2x-cloud" },
                    { name: "HORIZON", id: "2x-horizon" },
                    { name: "RESTART", id: "2x-restart" },
                    { name: "TEXT_SPRITE", id: "2x-text" },
                    { name: "TREX", id: "2x-trex" }
                ]*/
};
Runner.sounds = {
    BUTTON_PRESS: "offline-sound-press",
    HIT: "offline-sound-hit",
    SCORE: "offline-sound-reached"
};
Runner.keycodes = {
    JUMP: {"38": 1, "32": 1}, // Up, spacebar
    DUCK: {"40": 1}, // Down
    RESTART: {"13": 1} // Enter
};
Runner.events = {
    ANIM_END: "webkitAnimationEnd",
    CLICK: "click",
    KEYDOWN: "keydown",
    KEYUP: "keyup",
    MOUSEDOWN: "mousedown",
    MOUSEUP: "mouseup",
    RESIZE: "resize",
    TOUCHEND: "touchend",
    TOUCHSTART: "touchstart",
    VISIBILITY: "visibilitychange",
    BLUR: "blur",
    FOCUS: "focus",
    LOAD: "load"
};

function loadImages(runner) {
    let imageSources = Runner.imageSources.LDPI;
    let numImages = imageSources.length;
    for (let i = numImages - 1; i >= 0; i--) {
        let imgSource = imageSources[i];
        runner.images[imgSource.name] = document.getElementById(
            imgSource.id
        );
    }
    init(runner);
}

function init(runner) {
    runner.adjustDimensions();
    runner.setSpeed();
    runner.containerEl = document.createElement("div");
    runner.containerEl.className = Runner.classes.CONTAINER;
    // Player canvas container.
    runner.canvas = createCanvas(
        runner.containerEl,
        runner.dimensions.WIDTH,
        runner.dimensions.HEIGHT,
        Runner.classes.PLAYER
    );
    runner.canvasCtx = runner.canvas.getContext("2d");
    runner.canvasCtx.fillStyle = "#f7f7f7";
    runner.canvasCtx.fill();
    Runner.updateCanvasScaling(runner.canvas);
    // Horizon contains clouds, obstacles and the ground.
    runner.horizon = new Horizon(
        runner.canvas,
        runner.images,
        runner.dimensions,
        runner.config.GAP_COEFFICIENT
    );
    // Distance meter
    runner.distanceMeter = new DistanceMeter(
        runner.canvas,
        runner.images.TEXT_SPRITE,
        runner.dimensions.WIDTH
    );
    // Draw t-rex
    runner.tRex = new Trex(runner.canvas, runner.images.TREX);
    runner.outerContainerEl.appendChild(runner.containerEl);
    if (IS_MOBILE) {
        this.createTouchController();
    }
    runner.startListening();
    runner.update();
    window.addEventListener(
        Runner.events.RESIZE,
        runner.debounceResize.bind(runner)
    );
}

Runner.prototype = {
    updateConfigSetting: function (setting, value) {
        console.log(setting, value);
        //if (setting in this.config && value != undefined) {
        this.config[setting] = value;
        switch (setting) {
            case "GRAVITY":
            case "MIN_JUMP_HEIGHT":
            case "SPEED_DROP_COEFFICIENT":
                this.tRex.config[setting] = value;
                break;
            case "INITIAL_JUMP_VELOCITY":
                this.tRex.setJumpVelocity(value);
                break;
            case "SPEED":
                this.setSpeed(value);
                break;
        }
        //}
    },
    /*loadImages: function() {
        let imageSources = Runner.imageSources.LDPI;
        let numImages = imageSources.length;
        for (let i = numImages - 1; i >= 0; i--) {
            let imgSource = imageSources[i];
            this.images[imgSource.name] = document.getElementById(
                imgSource.id
            );
        }
        this.init();
    },*/
    loadSounds: function () {
        this.audioContext = new AudioContext();
        let resourceTemplate = document.getElementById(
            this.config.RESOURCE_TEMPLATE_ID
        ).content;
        for (let sound in Runner.sounds) {
            let soundSrc = resourceTemplate.getElementById(
                Runner.sounds[sound]
            ).src;
            soundSrc = soundSrc.substr(soundSrc.indexOf(",") + 1);
            let buffer = decodeBase64ToArrayBuffer(soundSrc);
            // Async, so no guarantee of order in array.
            this.audioContext.decodeAudioData(
                buffer,
                function (index, audioData) {
                    this.soundFx[index] = audioData;
                }.bind(this, sound)
            );
        }
    },
    setSpeed: function (opt_speed) {
        let speed = opt_speed || this.currentSpeed;
        // Reduce the speed on smaller mobile screens.
        if (this.dimensions.WIDTH < DEFAULT_WIDTH) {
            let mobileSpeed =
                speed *
                this.dimensions.WIDTH /
                DEFAULT_WIDTH *
                this.config.MOBILE_SPEED_COEFFICIENT;
            this.currentSpeed = mobileSpeed > speed ? speed : mobileSpeed;
        } else if (opt_speed) {
            this.currentSpeed = opt_speed;
        }
    },

    createTouchController: function () {
        this.touchController = document.createElement("div");
        this.touchController.className = Runner.classes.TOUCH_CONTROLLER;
    },
    debounceResize: function () {
        if (!this.resizeTimerId_) {
            this.resizeTimerId_ = setInterval(
                this.adjustDimensions.bind(this),
                250
            );
        }
    },
    adjustDimensions: function () {
        clearInterval(this.resizeTimerId_);
        this.resizeTimerId_ = null;
        let boxStyles = window.getComputedStyle(this.outerContainerEl);
        let padding = Number(
            boxStyles.paddingLeft.substr(
                0,
                boxStyles.paddingLeft.length - 2
            )
        );
        this.dimensions.WIDTH =
            this.outerContainerEl.offsetWidth - padding * 2;
        // Redraw the elements back onto the canvas.
        if (this.canvas) {
            this.canvas.width = this.dimensions.WIDTH;
            this.canvas.height = this.dimensions.HEIGHT;
            Runner.updateCanvasScaling(this.canvas);
            this.distanceMeter.calcXPos(this.dimensions.WIDTH);
            this.clearCanvas();
            this.horizon.update(0, 0, true);
            this.tRex.update(0);
            // Outer container and distance meter.
            if (this.activated || this.crashed) {
                this.containerEl.style.width = this.dimensions.WIDTH + "px";
                this.containerEl.style.height =
                    this.dimensions.HEIGHT + "px";
                this.distanceMeter.update(0, Math.ceil(this.distanceRan));
                this.stop();
            } else {
                this.tRex.draw(0, 0);
            }
            // Game over panel.
            if (this.crashed && this.gameOverPanel) {
                this.gameOverPanel.updateDimensions(this.dimensions.WIDTH);
                this.gameOverPanel.draw();
            }
        }
    },
    playIntro: function () {
        if (!this.started && !this.crashed) {
            this.playingIntro = true;
            this.tRex.playingIntro = true;
            // CSS animation definition.
            let keyframes =
                "@-webkit-keyframes intro { " +
                "from { width:" +
                Trex.config.WIDTH +
                "px }" +
                "to { width: " +
                this.dimensions.WIDTH +
                "px }" +
                "}";
            document.styleSheets[0].insertRule(keyframes, 0);
            this.containerEl.addEventListener(
                Runner.events.ANIM_END,
                this.startGame.bind(this)
            );
            this.containerEl.style.webkitAnimation =
                "intro .4s ease-out 1 both";
            this.containerEl.style.width = this.dimensions.WIDTH + "px";
            if (this.touchController) {
                this.outerContainerEl.appendChild(this.touchController);
            }
            this.activated = true;
            this.started = true;
        } else if (this.crashed) {
            this.restart();
        }
    },
    startGame: function () {
        this.runningTime = 0;
        this.playingIntro = false;
        this.tRex.playingIntro = false;
        this.containerEl.style.webkitAnimation = "";
        this.playCount++;
        // Handle tabbing off the page. Pause the current game.
        window.addEventListener(
            Runner.events.VISIBILITY,
            this.onVisibilityChange.bind(this)
        );
        window.addEventListener(
            Runner.events.BLUR,
            this.onVisibilityChange.bind(this)
        );
        window.addEventListener(
            Runner.events.FOCUS,
            this.onVisibilityChange.bind(this)
        );
    },
    clearCanvas: function () {
        this.canvasCtx.clearRect(
            0,
            0,
            this.dimensions.WIDTH,
            this.dimensions.HEIGHT
        );
    },
    update: function () {
        this.drawPending = false;
        let now = getTimeStamp();
        let deltaTime = now - (this.time || now);
        this.time = now;
        if (this.activated) {
            this.clearCanvas();
            if (this.tRex.jumping) {
                this.tRex.updateJump(deltaTime, this.config);
            }
            this.runningTime += deltaTime;
            let hasObstacles = this.runningTime > this.config.CLEAR_TIME;
            // First jump triggers the intro.
            if (this.tRex.jumpCount == 1 && !this.playingIntro) {
                this.playIntro();
            }
            // The horizon doesn't move until the intro is over.
            if (this.playingIntro) {
                this.horizon.update(0, this.currentSpeed, hasObstacles);
            } else {
                deltaTime = !this.started ? 0 : deltaTime;
                this.horizon.update(
                    deltaTime,
                    this.currentSpeed,
                    hasObstacles
                );
            }
            // Check for collisions.
            let collision =
                hasObstacles &&
                checkForCollision(this.horizon.obstacles[0], this.tRex);
            if (!collision) {
                this.distanceRan +=
                    this.currentSpeed * deltaTime / this.msPerFrame;
                if (this.currentSpeed < this.config.MAX_SPEED) {
                    this.currentSpeed += this.config.ACCELERATION;
                }
            } else {
                this.gameOver();
            }
            if (
                this.distanceMeter.getActualDistance(this.distanceRan) >
                this.distanceMeter.maxScore
            ) {
                this.distanceRan = 0;
            }
            let playAcheivementSound = this.distanceMeter.update(
                deltaTime,
                Math.ceil(this.distanceRan)
            );
            if (playAcheivementSound) {
                this.playSound(this.soundFx.SCORE);
            }
        }
        if (!this.crashed) {
            this.tRex.update(deltaTime);
            this.raq();
        }
    },
    handleEvent: function (e) {
        return function (evtType, events) {
            switch (evtType) {
                case events.KEYDOWN:
                case events.TOUCHSTART:
                case events.MOUSEDOWN:
                    this.onKeyDown(e);
                    break;
                case events.KEYUP:
                case events.TOUCHEND:
                case events.MOUSEUP:
                    this.onKeyUp(e);
                    break;
            }
        }.bind(this)(e.type, Runner.events);
    },
    startListening: function () {
        // Keys.
        document.addEventListener(Runner.events.KEYDOWN, this);
        document.addEventListener(Runner.events.KEYUP, this);
        if (IS_MOBILE) {
            // Mobile only touch devices.
            this.touchController.addEventListener(
                Runner.events.TOUCHSTART,
                this
            );
            this.touchController.addEventListener(
                Runner.events.TOUCHEND,
                this
            );
            this.containerEl.addEventListener(
                Runner.events.TOUCHSTART,
                this
            );
        } else {
            // Mouse.
            document.addEventListener(Runner.events.MOUSEDOWN, this);
            document.addEventListener(Runner.events.MOUSEUP, this);
        }
    },
    stopListening: function () {
        document.removeEventListener(Runner.events.KEYDOWN, this);
        document.removeEventListener(Runner.events.KEYUP, this);
        if (IS_MOBILE) {
            this.touchController.removeEventListener(
                Runner.events.TOUCHSTART,
                this
            );
            this.touchController.removeEventListener(
                Runner.events.TOUCHEND,
                this
            );
            this.containerEl.removeEventListener(
                Runner.events.TOUCHSTART,
                this
            );
        } else {
            document.removeEventListener(Runner.events.MOUSEDOWN, this);
            document.removeEventListener(Runner.events.MOUSEUP, this);
        }
    },
    onKeyDown: function (e) {
        if (e.target != this.detailsButton) {
            if (
                !this.crashed &&
                (Runner.keycodes.JUMP[String(e.keyCode)] ||
                    e.type == Runner.events.TOUCHSTART)
            ) {
                if (!this.activated) {
                    this.loadSounds();
                    this.activated = true;
                }
                if (!this.tRex.jumping) {
                    this.playSound(this.soundFx.BUTTON_PRESS);
                    this.tRex.startJump();
                }
            }
            if (
                this.crashed &&
                e.type == Runner.events.TOUCHSTART &&
                e.currentTarget == this.containerEl
            ) {
                this.restart();
            }
        }
        // Speed drop, activated only when jump key is not pressed.
        if (Runner.keycodes.DUCK[e.keyCode] && this.tRex.jumping) {
            e.preventDefault();
            this.tRex.setSpeedDrop();
        }
    },
    onKeyUp: function (e) {
        let keyCode = String(e.keyCode);
        let isjumpKey =
            Runner.keycodes.JUMP[keyCode] ||
            e.type == Runner.events.TOUCHEND ||
            e.type == Runner.events.MOUSEDOWN;
        if (this.isRunning() && isjumpKey) {
            this.tRex.endJump();
        } else if (Runner.keycodes.DUCK[keyCode]) {
            this.tRex.speedDrop = false;
        } else if (this.crashed) {
            // Check that enough time has elapsed before allowing jump key to restart.
            let deltaTime = getTimeStamp() - this.time;
            if (
                Runner.keycodes.RESTART[keyCode] ||
                (e.type == Runner.events.MOUSEUP &&
                    e.target == this.canvas) ||
                (deltaTime >= this.config.GAMEOVER_CLEAR_TIME &&
                    Runner.keycodes.JUMP[keyCode])
            ) {
                this.restart();
            }
        } else if (this.paused && isjumpKey) {
            this.play();
        }
    },
    raq: function () {
        if (!this.drawPending) {
            this.drawPending = true;
            this.raqId = requestAnimationFrame(this.update.bind(this));
        }
    },
    isRunning: function () {
        return !!this.raqId;
    },
    gameOver: function () {
        this.playSound(this.soundFx.HIT);
        vibrate(200);
        this.stop();
        this.crashed = true;
        this.distanceMeter.acheivement = false;
        this.tRex.update(100, Trex.status.CRASHED);
        // Game over panel.
        if (!this.gameOverPanel) {
            this.gameOverPanel = new GameOverPanel(
                this.canvas,
                this.images.TEXT_SPRITE,
                this.images.RESTART,
                this.dimensions
            );
        } else {
            this.gameOverPanel.draw();
        }
        // Update the high score.
        if (this.distanceRan > this.highestScore) {
            this.highestScore = Math.ceil(this.distanceRan);
            this.distanceMeter.setHighScore(this.highestScore);
        }
        // Reset the time clock.
        this.time = getTimeStamp();
    },
    stop: function () {
        this.activated = false;
        this.paused = true;
        cancelAnimationFrame(this.raqId);
        this.raqId = 0;
    },
    play: function () {
        if (!this.crashed) {
            this.activated = true;
            this.paused = false;
            this.tRex.update(0, Trex.status.RUNNING);
            this.time = getTimeStamp();
            this.update();
        }
    },
    restart: function () {
        if (!this.raqId) {
            this.playCount++;
            this.runningTime = 0;
            this.activated = true;
            this.crashed = false;
            this.distanceRan = 0;
            this.setSpeed(this.config.SPEED);
            this.time = getTimeStamp();
            this.containerEl.classList.remove(Runner.classes.CRASHED);
            this.clearCanvas();
            this.distanceMeter.reset(this.highestScore);
            this.horizon.reset();
            this.tRex.reset();
            this.playSound(this.soundFx.BUTTON_PRESS);
            this.update();
        }
    },
    onVisibilityChange: function (e) {
        if (document.hidden || document.webkitHidden || e.type == "blur") {
            this.stop();
        } else {
            this.play();
        }
    },
    playSound: function (soundBuffer) {
        if (soundBuffer) {
            let sourceNode = this.audioContext.createBufferSource();
            sourceNode.buffer = soundBuffer;
            sourceNode.connect(this.audioContext.destination);
            sourceNode.start(0);
        }
    }
};
// Updates the canvas size taking into account the backing store pixel ratio and the device pixel ratio.
// See article by Paul Lewis: https://www.html5rocks.com/en/tutorials/canvas/hidpi/
Runner.updateCanvasScaling = function (canvas, opt_width, opt_height) {
    let context = canvas.getContext("2d");
    // Query the letious pixel ratios
    let devicePxRatio = Math.floor(window.devicePixelRatio) || 1;
    let backingStoreRatio = Math.floor(context.webkitBackingStorePixelRatio) || 1;
    let ratio = devicePxRatio / backingStoreRatio;
    // Upscale the canvas if the two ratios don't match
    if (ratio !== 1) {
        let oldWidth = opt_width || canvas.width;
        let oldHeight = opt_height || canvas.height;
        canvas.width = oldWidth * ratio;
        canvas.height = oldHeight * ratio;
        canvas.style.width = oldWidth + "px";
        canvas.style.height = oldHeight + "px";
        // Scale the context to counter the fact that we've manually scaled our canvas element.
        context.scale(ratio, ratio);
        return true;
    }
    return false;
};
let getRandomNum = (min, max) => Math.floor(Math.random() * (max - min + 1)) + min;
let vibrate = duration => navigator.vibrate(duration);

function createCanvas(container, width, height, opt_classname) {
    let canvas = document.createElement("canvas");
    canvas.className = opt_classname
        ? Runner.classes.CANVAS + " " + opt_classname
        : Runner.classes.CANVAS;
    canvas.width = width;
    canvas.height = height;
    container.appendChild(canvas);
    return canvas;
}

function decodeBase64ToArrayBuffer(base64String) {
    let len = base64String.length / 4 * 3;
    let str = atob(base64String);
    let arrayBuffer = new ArrayBuffer(len);
    let bytes = new Uint8Array(arrayBuffer);
    for (let i = 0; i < len; i++) {
        bytes[i] = str.charCodeAt(i);
    }
    return bytes.buffer;
}

function getTimeStamp() {
    return performance.now();
}

function GameOverPanel(canvas, textSprite, restartImg, dimensions) {
    this.canvas = canvas;
    this.canvasCtx = canvas.getContext("2d");
    this.canvasDimensions = dimensions;
    this.textSprite = textSprite;
    this.restartImg = restartImg;
    this.draw();
}

GameOverPanel.dimensions = {
    TEXT_X: 0,
    TEXT_Y: 13,
    TEXT_WIDTH: 191,
    TEXT_HEIGHT: 11,
    RESTART_WIDTH: 36,
    RESTART_HEIGHT: 32
};
GameOverPanel.prototype = {
    updateDimensions: function (width, opt_height) {
        this.canvasDimensions.WIDTH = width;
        if (opt_height) {
            this.canvasDimensions.HEIGHT = opt_height;
        }
    },
    draw: function () {
        let dimensions = GameOverPanel.dimensions;
        let centerX = this.canvasDimensions.WIDTH / 2;
        // Game over text.
        let textSourceX = dimensions.TEXT_X;
        let textSourceY = dimensions.TEXT_Y;
        let textSourceWidth = dimensions.TEXT_WIDTH;
        let textSourceHeight = dimensions.TEXT_HEIGHT;
        let textTargetX = Math.round(centerX - dimensions.TEXT_WIDTH / 2);
        let textTargetY = Math.round(
            (this.canvasDimensions.HEIGHT - 25) / 3
        );
        let textTargetWidth = dimensions.TEXT_WIDTH;
        let textTargetHeight = dimensions.TEXT_HEIGHT;
        let restartSourceWidth = dimensions.RESTART_WIDTH;
        let restartSourceHeight = dimensions.RESTART_HEIGHT;
        let restartTargetX = centerX - dimensions.RESTART_WIDTH / 2;
        let restartTargetY = this.canvasDimensions.HEIGHT / 2;
        if (IS_HIDPI) {
            textSourceY *= 2;
            textSourceX *= 2;
            textSourceWidth *= 2;
            textSourceHeight *= 2;
            restartSourceWidth *= 2;
            restartSourceHeight *= 2;
        }
        // Game over text from sprite.
        this.canvasCtx.drawImage(
            this.textSprite,
            textSourceX,
            textSourceY,
            textSourceWidth,
            textSourceHeight,
            textTargetX,
            textTargetY,
            textTargetWidth,
            textTargetHeight
        );
        // Restart button.
        this.canvasCtx.drawImage(
            this.restartImg,
            0,
            0,
            restartSourceWidth,
            restartSourceHeight,
            restartTargetX,
            restartTargetY,
            dimensions.RESTART_WIDTH,
            dimensions.RESTART_HEIGHT
        );
    }
};

function checkForCollision(obstacle, tRex, opt_canvasCtx) {
    let obstacleBoxXPos = Runner.defaultDimensions.WIDTH + obstacle.xPos;
    // Adjustments are made to the bounding box as there is a 1 pixel white
    // border around the t-rex and obstacles.
    let tRexBox = new CollisionBox(
        tRex.xPos + 1,
        tRex.yPos + 1,
        tRex.config.WIDTH - 2,
        tRex.config.HEIGHT - 2
    );
    let obstacleBox = new CollisionBox(
        obstacle.xPos + 1,
        obstacle.yPos + 1,
        obstacle.typeConfig.width * obstacle.size - 2,
        obstacle.typeConfig.height - 2
    );
    // Debug outer box
    if (opt_canvasCtx) {
        drawCollisionBoxes(opt_canvasCtx, tRexBox, obstacleBox);
    }
    // Simple outer bounds check.
    if (boxCompare(tRexBox, obstacleBox)) {
        let collisionBoxes = obstacle.collisionBoxes;
        let tRexCollisionBoxes = Trex.collisionBoxes;
        // Detailed axis aligned box check.
        for (let t = 0; t < tRexCollisionBoxes.length; t++) {
            for (let i = 0; i < collisionBoxes.length; i++) {
                // Adjust the box to actual positions.
                let adjTrexBox = createAdjustedCollisionBox(
                    tRexCollisionBoxes[t],
                    tRexBox
                );
                let adjObstacleBox = createAdjustedCollisionBox(
                    collisionBoxes[i],
                    obstacleBox
                );
                let crashed = boxCompare(adjTrexBox, adjObstacleBox);
                // Draw boxes for debug.
                if (opt_canvasCtx) {
                    drawCollisionBoxes(
                        opt_canvasCtx,
                        adjTrexBox,
                        adjObstacleBox
                    );
                }
                if (crashed) {
                    return [adjTrexBox, adjObstacleBox];
                }
            }
        }
    }
    return false;
}

function createAdjustedCollisionBox(box, adjustment) {
    return new CollisionBox(
        box.x + adjustment.x,
        box.y + adjustment.y,
        box.width,
        box.height
    );
}

function drawCollisionBoxes(canvasCtx, tRexBox, obstacleBox) {
    canvasCtx.save();
    canvasCtx.strokeStyle = "#f00";
    canvasCtx.strokeRect(
        tRexBox.x,
        tRexBox.y,
        tRexBox.width,
        tRexBox.height
    );
    canvasCtx.strokeStyle = "#0f0";
    canvasCtx.strokeRect(
        obstacleBox.x,
        obstacleBox.y,
        obstacleBox.width,
        obstacleBox.height
    );
    canvasCtx.restore();
}

function boxCompare(tRexBox, obstacleBox) {
    let crashed = false;
    let tRexBoxX = tRexBox.x;
    let tRexBoxY = tRexBox.y;
    let obstacleBoxX = obstacleBox.x;
    let obstacleBoxY = obstacleBox.y;
    // Axis-Aligned Bounding Box method.
    if (
        tRexBox.x < obstacleBoxX + obstacleBox.width &&
        tRexBox.x + tRexBox.width > obstacleBoxX &&
        tRexBox.y < obstacleBox.y + obstacleBox.height &&
        tRexBox.height + tRexBox.y > obstacleBox.y
    ) {
        crashed = true;
    }
    return crashed;
}

function CollisionBox(x, y, w, h) {
    this.x = x;
    this.y = y;
    this.width = w;
    this.height = h;
}

function Obstacle(
    canvasCtx,
    type,
    obstacleImg,
    dimensions,
    gapCoefficient,
    speed
) {
    this.canvasCtx = canvasCtx;
    this.image = obstacleImg;
    this.typeConfig = type;
    this.gapCoefficient = gapCoefficient;
    this.size = getRandomNum(1, Obstacle.MAX_OBSTACLE_LENGTH);
    this.dimensions = dimensions;
    this.remove = false;
    this.xPos = 0;
    this.yPos = this.typeConfig.yPos;
    this.width = 0;
    this.collisionBoxes = [];
    this.gap = 0;
    this.init(speed);
}

Obstacle.MAX_GAP_COEFFICIENT = 1.5;
Obstacle.MAX_OBSTACLE_LENGTH = 3;
Obstacle.prototype = {
    init: function (speed) {
        this.cloneCollisionBoxes();
        // Only allow sizing if we're at the right speed.
        if (this.size > 1 && this.typeConfig.multipleSpeed > speed) {
            this.size = 1;
        }
        this.width = this.typeConfig.width * this.size;
        this.xPos = this.dimensions.WIDTH - this.width;
        this.draw();
        // Make collision box adjustments,
        // Central box is adjusted to the size as one box.
        // | |<->| | | |<--->| | | |<----->| |
        // | | 1 | | | |  2  | | | |   3   | |
        // |_|___|_| |_|_____|_| |_|_______|_|
        if (this.size > 1) {
            this.collisionBoxes[1].width =
                this.width -
                this.collisionBoxes[0].width -
                this.collisionBoxes[2].width;
            this.collisionBoxes[2].x =
                this.width - this.collisionBoxes[2].width;
        }
        this.gap = this.getGap(this.gapCoefficient, speed);
    },
    draw: function () {
        let sourceWidth = this.typeConfig.width;
        let sourceHeight = this.typeConfig.height;
        if (IS_HIDPI) {
            sourceWidth = sourceWidth * 2;
            sourceHeight = sourceHeight * 2;
        }
        // Sprite
        let sourceX = sourceWidth * this.size * (0.5 * (this.size - 1));
        this.canvasCtx.drawImage(
            this.image,
            sourceX,
            0,
            sourceWidth * this.size,
            sourceHeight,
            this.xPos,
            this.yPos,
            this.typeConfig.width * this.size,
            this.typeConfig.height
        );
    },
    update: function (deltaTime, speed) {
        if (!this.remove) {
            this.xPos -= Math.floor(speed * FPS / 1000 * deltaTime);
            this.draw();
            if (!this.isVisible()) {
                this.remove = true;
            }
        }
    },
    getGap: function (gapCoefficient, speed) {
        let minGap = Math.round(
            this.width * speed + this.typeConfig.minGap * gapCoefficient
        );
        let maxGap = Math.round(minGap * Obstacle.MAX_GAP_COEFFICIENT);
        return getRandomNum(minGap, maxGap);
    },
    isVisible: function () {
        return this.xPos + this.width > 0;
    },
    cloneCollisionBoxes: function () {
        let collisionBoxes = this.typeConfig.collisionBoxes;
        for (let i = collisionBoxes.length - 1; i >= 0; i--) {
            this.collisionBoxes[i] = new CollisionBox(
                collisionBoxes[i].x,
                collisionBoxes[i].y,
                collisionBoxes[i].width,
                collisionBoxes[i].height
            );
        }
    }
};
Obstacle.types = [
    {
        type: "CACTUS_SMALL",
        className: " cactus cactus-small ",
        width: 17,
        height: 35,
        yPos: 105,
        multipleSpeed: 3,
        minGap: 120,
        collisionBoxes: [
            new CollisionBox(0, 7, 5, 27),
            new CollisionBox(4, 0, 6, 34),
            new CollisionBox(10, 4, 7, 14)
        ]
    },
    {
        type: "CACTUS_LARGE",
        className: " cactus cactus-large ",
        width: 25,
        height: 50,
        yPos: 90,
        multipleSpeed: 6,
        minGap: 120,
        collisionBoxes: [
            new CollisionBox(0, 12, 7, 38),
            new CollisionBox(8, 0, 7, 49),
            new CollisionBox(13, 10, 10, 38)
        ]
    }
];

function Trex(canvas, image) {
    this.canvas = canvas;
    this.canvasCtx = canvas.getContext("2d");
    this.image = image;
    this.xPos = 0;
    this.yPos = 0;
    // Position when on the ground.
    this.groundYPos = 0;
    this.currentFrame = 0;
    this.currentAnimFrames = [];
    this.blinkDelay = 0;
    this.animStartTime = 0;
    this.timer = 0;
    this.msPerFrame = 1000 / FPS;
    this.config = Trex.config;
    // Current status.
    this.status = Trex.status.WAITING;
    this.jumping = false;
    this.jumpVelocity = 0;
    this.reachedMinHeight = false;
    this.speedDrop = false;
    this.jumpCount = 0;
    this.jumpspotX = 0;
    this.init();
}

Trex.config = {
    DROP_VELOCITY: -5,
    GRAVITY: 0.6,
    HEIGHT: 47,
    INIITAL_JUMP_VELOCITY: -10,
    INTRO_DURATION: 1500,
    MAX_JUMP_HEIGHT: 30,
    MIN_JUMP_HEIGHT: 30,
    SPEED_DROP_COEFFICIENT: 3,
    SPRITE_WIDTH: 262,
    START_X_POS: 50,
    WIDTH: 44
};
Trex.collisionBoxes = [
    new CollisionBox(1, -1, 30, 26),
    new CollisionBox(32, 0, 8, 16),
    new CollisionBox(10, 35, 14, 8),
    new CollisionBox(1, 24, 29, 5),
    new CollisionBox(5, 30, 21, 4),
    new CollisionBox(9, 34, 15, 4)
];
Trex.status = {
    CRASHED: "CRASHED",
    JUMPING: "JUMPING",
    RUNNING: "RUNNING",
    WAITING: "WAITING"
};
Trex.animFrames = {
    WAITING: {
        frames: [44, 0],
        msPerFrame: 1000 / 3
    },
    RUNNING: {
        frames: [88, 132],
        msPerFrame: 1000 / 12
    },
    CRASHED: {
        frames: [220],
        msPerFrame: 1000 / 60
    },
    JUMPING: {
        frames: [0],
        msPerFrame: 1000 / 60
    }
};
Trex.prototype = {
    init: function () {
        this.blinkDelay = this.setBlinkDelay();
        this.groundYPos =
            Runner.defaultDimensions.HEIGHT -
            this.config.HEIGHT -
            Runner.config.BOTTOM_PAD;
        this.yPos = this.groundYPos;
        this.minJumpHeight = this.groundYPos - this.config.MIN_JUMP_HEIGHT;
        this.draw(0, 0);
        this.update(0, Trex.status.WAITING);
    },
    setJumpVelocity: function (setting) {
        this.config.INIITAL_JUMP_VELOCITY = -setting;
        this.config.DROP_VELOCITY = -setting / 2;
    },
    update: function (deltaTime, opt_status) {
        this.timer += deltaTime;
        // Update the status.
        if (opt_status) {
            this.status = opt_status;
            this.currentFrame = 0;
            this.msPerFrame = Trex.animFrames[opt_status].msPerFrame;
            this.currentAnimFrames = Trex.animFrames[opt_status].frames;
            if (opt_status == Trex.status.WAITING) {
                this.animStartTime = getTimeStamp();
                this.setBlinkDelay();
            }
        }
        // Game intro animation, T-rex moves in from the left.
        if (this.playingIntro && this.xPos < this.config.START_X_POS) {
            this.xPos += Math.round(
                this.config.START_X_POS /
                this.config.INTRO_DURATION *
                deltaTime
            );
        }
        if (this.status == Trex.status.WAITING) {
            this.blink(getTimeStamp());
        } else {
            this.draw(this.currentAnimFrames[this.currentFrame], 0);
        }
        // Update the frame position.
        if (this.timer >= this.msPerFrame) {
            this.currentFrame = this.currentFrame ===
            this.currentAnimFrames.length - 1
                ? 0
                : this.currentFrame + 1;
            this.timer = 0;
        }
    },
    draw: function (x, y) {
        let sourceX = x;
        let sourceY = y;
        let sourceWidth = this.config.WIDTH;
        let sourceHeight = this.config.HEIGHT;
        if (IS_HIDPI) {
            sourceX *= 2;
            sourceY *= 2;
            sourceWidth *= 2;
            sourceHeight *= 2;
        }
        this.canvasCtx.drawImage(
            this.image,
            sourceX,
            sourceY,
            sourceWidth,
            sourceHeight,
            this.xPos,
            this.yPos,
            this.config.WIDTH,
            this.config.HEIGHT
        );
    },
    setBlinkDelay: function () {
        this.blinkDelay = Math.ceil(Math.random() * Trex.BLINK_TIMING);
    },
    blink: function (time) {
        let deltaTime = time - this.animStartTime;
        if (deltaTime >= this.blinkDelay) {
            this.draw(this.currentAnimFrames[this.currentFrame], 0);
            if (this.currentFrame == 1) {
                // Set new random delay to blink.
                this.setBlinkDelay();
                this.animStartTime = time;
            }
        }
    },
    startJump: function () {
        if (!this.jumping) {
            this.update(0, Trex.status.JUMPING);
            this.jumpVelocity = this.config.INIITAL_JUMP_VELOCITY;
            this.jumping = true;
            this.reachedMinHeight = false;
            this.speedDrop = false;
        }
    },
    endJump: function () {
        if (
            this.reachedMinHeight &&
            this.jumpVelocity < this.config.DROP_VELOCITY
        ) {
            this.jumpVelocity = this.config.DROP_VELOCITY;
        }
    },
    updateJump: function (deltaTime) {
        let msPerFrame = Trex.animFrames[this.status].msPerFrame;
        let framesElapsed = deltaTime / msPerFrame;
        // Speed drop makes Trex fall faster.
        if (this.speedDrop) {
            this.yPos += Math.round(
                this.jumpVelocity *
                this.config.SPEED_DROP_COEFFICIENT *
                framesElapsed
            );
        } else {
            this.yPos += Math.round(this.jumpVelocity * framesElapsed);
        }
        this.jumpVelocity += this.config.GRAVITY * framesElapsed;
        // Minimum height has been reached.
        if (this.yPos < this.minJumpHeight || this.speedDrop) {
            this.reachedMinHeight = true;
        }
        // Reached max height
        if (this.yPos < this.config.MAX_JUMP_HEIGHT || this.speedDrop) {
            this.endJump();
        }
        // Back down at ground level. Jump completed.
        if (this.yPos > this.groundYPos) {
            this.reset();
            this.jumpCount++;
        }
        this.update(deltaTime);
    },
    setSpeedDrop: function () {
        this.speedDrop = true;
        this.jumpVelocity = 1;
    },
    reset: function () {
        this.yPos = this.groundYPos;
        this.jumpVelocity = 0;
        this.jumping = false;
        this.update(0, Trex.status.RUNNING);
        this.midair = false;
        this.speedDrop = false;
        this.jumpCount = 0;
    }
};

function DistanceMeter(canvas, spriteSheet, canvasWidth) {
    this.canvas = canvas;
    this.canvasCtx = canvas.getContext("2d");
    this.image = spriteSheet;
    this.x = 0;
    this.y = 5;
    this.currentDistance = 0;
    this.maxScore = 0;
    this.highScore = 0;
    this.container = null;
    this.digits = [];
    this.acheivement = false;
    this.defaultString = "";
    this.flashTimer = 0;
    this.flashIterations = 0;
    this.config = DistanceMeter.config;
    this.init(canvasWidth);
}

DistanceMeter.dimensions = {
    WIDTH: 10,
    HEIGHT: 13,
    DEST_WIDTH: 11
};
DistanceMeter.yPos = [0, 13, 27, 40, 53, 67, 80, 93, 107, 120];
DistanceMeter.config = {
    // Number of digits.
    MAX_DISTANCE_UNITS: 5,
    // Distance that causes achievement animation.
    ACHIEVEMENT_DISTANCE: 100,
    // Used for conversion from pixel distance to a scaled unit.
    COEFFICIENT: 0.025,
    // Flash duration in milliseconds.
    FLASH_DURATION: 1000 / 4,
    // Flash iterations for achievement animation.
    FLASH_ITERATIONS: 3
};
DistanceMeter.prototype = {
    init: function (width) {
        let maxDistanceStr = "";
        this.calcXPos(width);
        this.maxScore = this.config.MAX_DISTANCE_UNITS;
        for (let i = 0; i < this.config.MAX_DISTANCE_UNITS; i++) {
            this.draw(i, 0);
            this.defaultString += "0";
            maxDistanceStr += "9";
        }
        this.maxScore = parseInt(maxDistanceStr);
    },
    calcXPos: function (canvasWidth) {
        this.x =
            canvasWidth -
            DistanceMeter.dimensions.DEST_WIDTH *
            (this.config.MAX_DISTANCE_UNITS + 1);
    },
    draw: function (digitPos, value, opt_highScore) {
        let sourceWidth = DistanceMeter.dimensions.WIDTH;
        let sourceHeight = DistanceMeter.dimensions.HEIGHT;
        let sourceX = DistanceMeter.dimensions.WIDTH * value;
        let targetX = digitPos * DistanceMeter.dimensions.DEST_WIDTH;
        let targetY = this.y;
        let targetWidth = DistanceMeter.dimensions.WIDTH;
        let targetHeight = DistanceMeter.dimensions.HEIGHT;
        // For high DPI we 2x source values.
        if (IS_HIDPI) {
            sourceWidth *= 2;
            sourceHeight *= 2;
            sourceX *= 2;
        }
        this.canvasCtx.save();
        if (opt_highScore) {
            // Left of the current score.
            let highScoreX =
                this.x -
                this.config.MAX_DISTANCE_UNITS *
                2 *
                DistanceMeter.dimensions.WIDTH;
            this.canvasCtx.translate(highScoreX, this.y);
        } else {
            this.canvasCtx.translate(this.x, this.y);
        }
        this.canvasCtx.drawImage(
            this.image,
            sourceX,
            0,
            sourceWidth,
            sourceHeight,
            targetX,
            targetY,
            targetWidth,
            targetHeight
        );
        this.canvasCtx.restore();
    },
    getActualDistance: function (distance) {
        return distance
            ? Math.round(distance * this.config.COEFFICIENT)
            : 0;
    },
    update: function (deltaTime, distance) {
        let paint = true;
        let playSound = false;
        if (!this.acheivement) {
            distance = this.getActualDistance(distance);
            if (distance > 0) {
                // Acheivement unlocked
                if (distance % this.config.ACHIEVEMENT_DISTANCE == 0) {
                    // Flash score and play sound.
                    this.acheivement = true;
                    this.flashTimer = 0;
                    playSound = true;
                }
                // Create a string representation of the distance with leading 0.
                let distanceStr = (this.defaultString + distance).substr(
                    -this.config.MAX_DISTANCE_UNITS
                );
                this.digits = distanceStr.split("");
            } else {
                this.digits = this.defaultString.split("");
            }
        } else {
            // Control flashing of the score on reaching acheivement.
            if (this.flashIterations <= this.config.FLASH_ITERATIONS) {
                this.flashTimer += deltaTime;
                if (this.flashTimer < this.config.FLASH_DURATION) {
                    paint = false;
                } else if (
                    this.flashTimer >
                    this.config.FLASH_DURATION * 2
                ) {
                    this.flashTimer = 0;
                    this.flashIterations++;
                }
            } else {
                this.acheivement = false;
                this.flashIterations = 0;
                this.flashTimer = 0;
            }
        }
        // Draw the digits if not flashing.
        if (paint) {
            for (let i = this.digits.length - 1; i >= 0; i--) {
                this.draw(i, parseInt(this.digits[i]));
            }
        }
        this.drawHighScore();
        return playSound;
    },
    drawHighScore: function () {
        this.canvasCtx.save();
        this.canvasCtx.globalAlpha = 0.8;
        for (let i = this.highScore.length - 1; i >= 0; i--) {
            this.draw(i, parseInt(this.highScore[i], 10), true);
        }
        this.canvasCtx.restore();
    },
    setHighScore: function (distance) {
        distance = this.getActualDistance(distance);
        let highScoreStr = (this.defaultString + distance).substr(
            -this.config.MAX_DISTANCE_UNITS
        );
        this.highScore = ["10", "11", ""].concat(highScoreStr.split(""));
    },
    reset: function () {
        this.update(0);
        this.acheivement = false;
    }
};

function Cloud(canvas, cloudImg, containerWidth) {
    this.canvas = canvas;
    this.canvasCtx = this.canvas.getContext("2d");
    this.image = cloudImg;
    this.containerWidth = containerWidth;
    this.xPos = containerWidth;
    this.yPos = 0;
    this.remove = false;
    this.cloudGap = getRandomNum(
        Cloud.config.MIN_CLOUD_GAP,
        Cloud.config.MAX_CLOUD_GAP
    );
    this.init();
}

Cloud.config = {
    HEIGHT: 14,
    MAX_CLOUD_GAP: 400,
    MAX_SKY_LEVEL: 30,
    MIN_CLOUD_GAP: 100,
    MIN_SKY_LEVEL: 71,
    WIDTH: 46
};
Cloud.prototype = {
    init: function () {
        this.yPos = getRandomNum(
            Cloud.config.MAX_SKY_LEVEL,
            Cloud.config.MIN_SKY_LEVEL
        );
        this.draw();
    },
    draw: function () {
        this.canvasCtx.save();
        let sourceWidth = Cloud.config.WIDTH;
        let sourceHeight = Cloud.config.HEIGHT;
        if (IS_HIDPI) {
            sourceWidth = sourceWidth * 2;
            sourceHeight = sourceHeight * 2;
        }
        this.canvasCtx.drawImage(
            this.image,
            0,
            0,
            sourceWidth,
            sourceHeight,
            this.xPos,
            this.yPos,
            Cloud.config.WIDTH,
            Cloud.config.HEIGHT
        );
        this.canvasCtx.restore();
    },
    update: function (speed) {
        if (!this.remove) {
            this.xPos -= Math.ceil(speed);
            this.draw();
            // Mark as removeable if no longer in the canvas.
            if (!this.isVisible()) {
                this.remove = true;
            }
        }
    },
    isVisible: function () {
        return this.xPos + Cloud.config.WIDTH > 0;
    }
};

function HorizonLine(canvas, bgImg) {
    this.image = bgImg;
    this.canvas = canvas;
    this.canvasCtx = canvas.getContext("2d");
    this.sourceDimensions = {};
    this.dimensions = HorizonLine.dimensions;
    this.sourceXPos = [0, this.dimensions.WIDTH];
    this.xPos = [];
    this.yPos = 0;
    this.bumpThreshold = 0.5;
    this.setSourceDimensions();
    this.draw();
}

HorizonLine.dimensions = {
    WIDTH: 600,
    HEIGHT: 12,
    YPOS: 127
};
HorizonLine.prototype = {
    setSourceDimensions: function () {
        for (let dimension in HorizonLine.dimensions) {
            if (IS_HIDPI) {
                if (dimension != "YPOS") {
                    this.sourceDimensions[dimension] =
                        HorizonLine.dimensions[dimension] * 2;
                }
            } else {
                this.sourceDimensions[dimension] =
                    HorizonLine.dimensions[dimension];
            }
            this.dimensions[dimension] = HorizonLine.dimensions[dimension];
        }
        this.xPos = [0, HorizonLine.dimensions.WIDTH];
        this.yPos = HorizonLine.dimensions.YPOS;
    },
    getRandomType: function () {
        return Math.random() > this.bumpThreshold
            ? this.dimensions.WIDTH
            : 0;
    },
    draw: function () {
        this.canvasCtx.drawImage(
            this.image,
            this.sourceXPos[0],
            0,
            this.sourceDimensions.WIDTH,
            this.sourceDimensions.HEIGHT,
            this.xPos[0],
            this.yPos,
            this.dimensions.WIDTH,
            this.dimensions.HEIGHT
        );
        this.canvasCtx.drawImage(
            this.image,
            this.sourceXPos[1],
            0,
            this.sourceDimensions.WIDTH,
            this.sourceDimensions.HEIGHT,
            this.xPos[1],
            this.yPos,
            this.dimensions.WIDTH,
            this.dimensions.HEIGHT
        );
    },
    updateXPos: function (pos, increment) {
        let line1 = pos;
        let line2 = pos == 0 ? 1 : 0;
        this.xPos[line1] -= increment;
        this.xPos[line2] = this.xPos[line1] + this.dimensions.WIDTH;
        if (this.xPos[line1] <= -this.dimensions.WIDTH) {
            this.xPos[line1] += this.dimensions.WIDTH * 2;
            this.xPos[line2] = this.xPos[line1] - this.dimensions.WIDTH;
            this.sourceXPos[line1] = this.getRandomType();
        }
    },
    update: function (deltaTime, speed) {
        let increment = Math.floor(speed * (FPS / 1000) * deltaTime);
        if (this.xPos[0] <= 0) {
            this.updateXPos(0, increment);
        } else {
            this.updateXPos(1, increment);
        }
        this.draw();
    },
    reset: function () {
        this.xPos[0] = 0;
        this.xPos[1] = HorizonLine.dimensions.WIDTH;
    }
};

function Horizon(canvas, images, dimensions, gapCoefficient) {
    this.canvas = canvas;
    this.canvasCtx = this.canvas.getContext("2d");
    this.config = Horizon.config;
    this.dimensions = dimensions;
    this.gapCoefficient = gapCoefficient;
    this.obstacles = [];
    this.horizonOffsets = [0, 0];
    this.cloudFrequency = this.config.CLOUD_FREQUENCY;
    // Cloud
    this.clouds = [];
    this.cloudImg = images.CLOUD;
    this.cloudSpeed = this.config.BG_CLOUD_SPEED;
    // Horizon
    this.horizonImg = images.HORIZON;
    this.horizonLine = null;
    // Obstacles
    this.obstacleImgs = {
        CACTUS_SMALL: images.CACTUS_SMALL,
        CACTUS_LARGE: images.CACTUS_LARGE
    };
    this.init();
}

Horizon.config = {
    BG_CLOUD_SPEED: 0.2,
    BUMPY_THRESHOLD: 0.3,
    CLOUD_FREQUENCY: 0.5,
    HORIZON_HEIGHT: 16,
    MAX_CLOUDS: 6
};
Horizon.prototype = {
    init: function () {
        this.addCloud();
        this.horizonLine = new HorizonLine(this.canvas, this.horizonImg);
    },
    // updateObstacles used as an override to prevent the obstacles from being updated / added in the ease in section.
    update: function (deltaTime, currentSpeed, updateObstacles) {
        this.runningTime += deltaTime;
        this.horizonLine.update(deltaTime, currentSpeed);
        this.updateClouds(deltaTime, currentSpeed);
        if (updateObstacles) {
            this.updateObstacles(deltaTime, currentSpeed);
        }
    },
    updateClouds: function (deltaTime, speed) {
        let cloudSpeed = this.cloudSpeed / 1000 * deltaTime * speed;
        let numClouds = this.clouds.length;
        if (numClouds) {
            for (let i = numClouds - 1; i >= 0; i--) {
                this.clouds[i].update(cloudSpeed);
            }
            let lastCloud = this.clouds[numClouds - 1];
            // Check for adding a new cloud.
            if (
                numClouds < this.config.MAX_CLOUDS &&
                this.dimensions.WIDTH - lastCloud.xPos >
                lastCloud.cloudGap &&
                this.cloudFrequency > Math.random()
            ) {
                this.addCloud();
            }
            // Remove expired clouds.
            this.clouds = this.clouds.filter(function (obj) {
                return !obj.remove;
            });
        }
    },
    updateObstacles: function (deltaTime, currentSpeed) {
        // Obstacles, move to Horizon layer.
        let updatedObstacles = this.obstacles.slice(0);
        for (let i = 0; i < this.obstacles.length; i++) {
            let obstacle = this.obstacles[i];
            obstacle.update(deltaTime, currentSpeed);
            // Clean up existing obstacles.
            if (obstacle.remove) {
                updatedObstacles.shift();
            }
        }
        this.obstacles = updatedObstacles;
        if (this.obstacles.length > 0) {
            let lastObstacle = this.obstacles[this.obstacles.length - 1];
            if (
                lastObstacle &&
                !lastObstacle.followingObstacleCreated &&
                lastObstacle.isVisible() &&
                lastObstacle.xPos + lastObstacle.width + lastObstacle.gap <
                this.dimensions.WIDTH
            ) {
                this.addNewObstacle(currentSpeed);
                lastObstacle.followingObstacleCreated = true;
            }
        } else {
            // Create new obstacles.
            this.addNewObstacle(currentSpeed);
        }
    },
    addNewObstacle: function (currentSpeed) {
        let obstacleTypeIndex = getRandomNum(0, Obstacle.types.length - 1);
        let obstacleType = Obstacle.types[obstacleTypeIndex];
        let obstacleImg = this.obstacleImgs[obstacleType.type];
        this.obstacles.push(
            new Obstacle(
                this.canvasCtx,
                obstacleType,
                obstacleImg,
                this.dimensions,
                this.gapCoefficient,
                currentSpeed
            )
        );
    },
    reset: function () {
        this.obstacles = [];
        ``
        this.horizonLine.reset();
    },
    resize: function (width, height) {
        this.canvas.width = width;
        this.canvas.height = height;
    },
    addCloud: function () {
        this.clouds.push(
            new Cloud(this.canvas, this.cloudImg, this.dimensions.WIDTH)
        );
    }
};


//game script ends here


let $userId = $('#userId').val();
let $testId = $('#testId').val();

let sub = $('#submit');
sub.hide();

let request = true;

$.post('/history/user/' + $userId + '/' + $testId, function (timerData) {

    let startTime = new Date(timerData.startTime).getTime();


    let hours = (startTime > 0) ? Math.floor((startTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) : 0;
    let minutes = (startTime > 0) ? Math.floor((startTime % (1000 * 60 * 60)) / (1000 * 60)) : 0;
    let seconds = (startTime > 0) ? Math.floor((startTime % (1000 * 60)) / 1000) : 0;

    let now = new Date(timerData.currentTime).getTime();

    hours = (now > 0) ? Math.floor((now % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)) : 0;
    minutes = (now > 0) ? Math.floor((now % (1000 * 60 * 60)) / (1000 * 60)) : 0;
    seconds = (now > 0) ? Math.floor((now % (1000 * 60)) / 1000) : 0;


    let interval = setInterval(function () {

        if (now >= startTime) {
            clearInterval(interval);
            sub.click();
        }
        now = now + 1000;
        console.log('Now is ' + now);
    }, 1000);


});

if (navigator.userAgent.toLowerCase().indexOf('chrome') <= -1) {
    hideClass(".onlyforchrome");
}

if (navigator.userAgent.toLowerCase().indexOf('chrome') > -1) {
    new Runner('.interstitial-wrapper');
} else {
    document.getElementById("main-frame-notchrome").style.display = "";
}
