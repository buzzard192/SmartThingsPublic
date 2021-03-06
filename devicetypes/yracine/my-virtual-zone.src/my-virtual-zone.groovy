/**
 *  My Virtual Zone
 *  v1.0.4
 *
 *  Copyright 2017 Yves Racine
 *  LinkedIn profile: ca.linkedin.com/pub/yves-racine-m-sc-a/0/406/4b/
 *
 *  Developer retains all right, title, copyright, and interest, including all copyright, patent rights, trade secret 
 *  in the Background technology. May be subject to consulting fees under the Agreement between the Developer and the Customer. 
 *  Developer grants a non exclusive perpetual license to use the Background technology in the Software developed for and delivered 
 *  to Customer under this Agreement. However, the Customer shall make no commercial use of the Background technology without
 *  Developer's written consent.
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
  *  Software Distribution is restricted and shall be done only with Developer's written approval.
 *
 *
 */
preferences {

	//	Preferences are optional 
 
	input("trace", "bool", title: "trace", description:
		"Set it to true to enable tracing (no spaces)\n or leave it empty (no tracing)")
	input("logFilter", "number",title: "(1=ERROR only,2=<1+WARNING>,3=<2+INFO>,4=<3+DEBUG>,5=<4+TRACE>)",  range: "1..5",
 		description: "optional" )  
}
metadata {
	// Automatically generated. Make future change here.
	definition (name: "My Virtual Zone", namespace: "yracine", author: "Yves Racine") {
		capability "Temperature Measurement"
		capability "Sensor"	
		capability "Actuator"	
		capability "Motion Sensor"
		capability "Switch"
		capability "Contact Sensor"
	}

	command "deltaLevelUp"
	command "deltaLevelDown"
	command "setZoneActive"
	command "setZoneInactive"
    
    
	attribute "tempDelta", "string"
	attribute "mode", "string"
	attribute "activeZone", "string"
    
	// UI tile definitions
	tiles(scale: 2) {
		multiAttributeTile(name:"multigeneric", type: "generic", width: 2, height: 2,canChangeIcon: true){
			tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
				attributeState("default", label:'${currentValue}', unit:"dF", backgroundColor:"#269bd2") 
			}

		}
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "off", label: '${name}', action: "switch.on", icon: "st.vents.vent", backgroundColor: "#ffffff",defaultState:true
			state "on", label: '${name}', action: "switch.off", icon: "st.vents.vent-open", backgroundColor: "#79b821"
		}
		valueTile("temperature", "device.temperature", width: 6, height: 4) {
			state("temperature", label:'${currentValue}', unit:"F",
				backgroundColors: getBackgroundColors())
		}
		standardTile("motion", "device.motion", width: 2, height: 2) {
			state("active", label:'motion', icon:"st.motion.motion.active", backgroundColor:"#00a0dc")
			state("inactive", label:'no motion', icon:"st.motion.motion.inactive", backgroundColor:"#ffffff", defaultState:true)
		}
		standardTile("contact", "device.contact", width: 2, height: 2) {
			state("open", label:'${name}', icon:"st.contact.contact.open", backgroundColor:"#e86d13")
			state("closed", label:'${name}', icon:"st.contact.contact.closed", backgroundColor:"#00a0dc",defaultState:true)
		}
		standardTile("activeZone", "device.activeZone", width: 2, height: 2) {
			state("true", label:'Active', icon:"st.motion.motion.active", backgroundColor:"#00a0dc",
            		defaultState:true, nextState:"false", action: "setZoneInactive"
				)
			state("false", label:'Inactive', icon:"st.motion.motion.inactive", backgroundColor:"#ffffff",
            		nextState:"false", action: "setZoneActive"
				)                    
		}
 		standardTile("mode", "device.mode", inactiveLabel: false,
			decoration: "flat", width: 2, height: 2,) {
			state "heat", label: 'Mode ${name}',
				icon: "http://raw.githubusercontent.com/yracine/device-type.myecobee/master/icons/heatMode.png", backgroundColor: "#ffffff"
			state "off", label: 'Mode ${name}', 
				icon: "st.Outdoor.outdoor19", backgroundColor: "#ffffff", defaultState:true
			state "cool", label: 'Mode ${name}', 
				icon: "http://raw.githubusercontent.com/yracine/device-type.myecobee/master/icons/coolMode.png", backgroundColor: "#ffffff"
			state "auto", label: 'Mode ${name}', 
				icon: "http://raw.githubusercontent.com/yracine/device-type.myecobee/master/icons/autoMode.png",
 			backgroundColor: "#ffffff"
		}
		valueTile("thermostatSetpoint", "device.thermostatSetpoint", width: 2, height: 2, inactiveLabel: false) {
			state ("default", label:'Setpoint ${currentValue}', unit:"F", 
			backgroundColors:[
				[value: 0, color: "#153591"],
				[value: 7, color: "#1e9cbb"],
				[value: 15, color: "#90d2a7"],
				[value: 23, color: "#44b621"],
				[value: 29, color: "#f1d801"],
				[value: 35, color: "#d04e00"],
				[value: 37, color: "#bc2323"],
				// Fahrenheit Color Range
				[value: 31, color: "#153591"],
				[value: 44, color: "#1e9cbb"],
				[value: 59, color: "#90d2a7"],
				[value: 74, color: "#44b621"],
				[value: 84, color: "#f1d801"],
				[value: 95, color: "#d04e00"],
				[value: 96, color: "#bc2323"]
			])	            
		}
                     
		valueTile("name", "device.name", inactiveLabel: false, width: 6,height: 1) {
			state "default", label: '${currentValue}', 
			backgroundColor: "#ffffff"
		}
		valueTile("tempDelta", "device.tempDelta", width: 2, height: 2, inactiveLabel: false) {
			state ("default", label:'TempDelta ${currentValue}', unit:"F", 
			backgroundColors:[
				[value: 0, color: "#153591"],
				[value: 7, color: "#1e9cbb"],
				[value: 15, color: "#90d2a7"],
				[value: 23, color: "#44b621"],
				[value: 29, color: "#f1d801"],
				[value: 35, color: "#d04e00"],
				[value: 37, color: "#bc2323"],
				// Fahrenheit Color Range
				[value: 31, color: "#153591"],
				[value: 44, color: "#1e9cbb"],
				[value: 59, color: "#90d2a7"],
				[value: 74, color: "#44b621"],
				[value: 84, color: "#f1d801"],
				[value: 95, color: "#d04e00"],
				[value: 96, color: "#bc2323"]
			])	            
		}

		standardTile("deltaLevelDown", "device.tempDelta", width: 2, height: 2, canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "default", label: '', action:"deltaLevelDown", icon: "st.thermostat.thermostat-down", backgroundColor: "#ffffff"

		}
		standardTile("deltaLevelUp", "device.tempDelta", width: 2, height: 2, canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "default", label: '', action:"deltaLevelUp", icon: "st.thermostat.thermostat-up", backgroundColor: "#ffffff"
		}
        
	main(["multigeneric"])
		details([
		"multigeneric",
		"mode",        
		"thermostatSetpoint",
		"motion",
		"tempDelta", "deltaLevelUp", "deltaLevelDown",
		"contact",
		"switch",        
		"activeZone"
		])
	}
}

void installed() {
	initialize()
	if (settings.trace) { 
			log.debug("installed>$device.displayName installed with settings: ${settings.inspect()}, state=${state.inspect()}")
	}
}

void initialize() {
	state?.scale=getTemperatureScale() 
	def virtual_info = device.deviceNetworkId.tokenize('.')
	state?.indiceZone = virtual_info.last()
	sendEvent(name:"tempDelta", value:0,isDisplayed:false)    
}

/* Ping is used by Device-Watch in attempt to reach the device
*/
def ping() {
	poll()
}

def updated() {
	initialize()
	traceEvent(settings.logFilter,"updated>$device.displayName updated with settings: ${settings.inspect()},state=${state.inspect()}",
		settings.trace,get_LOG_TRACE())        
	        
}


def getBackgroundColors() {
	def results
	if (state?.scale =='C') {
				// Celsius Color Range
		results=
			[        
				[value: 0, color: "#153591"],
				[value: 7, color: "#1e9cbb"],
				[value: 15, color: "#90d2a7"],
				[value: 23, color: "#44b621"],
				[value: 29, color: "#f1d801"],
				[value: 35, color: "#d04e00"],
				[value: 37, color: "#bc2323"]
			]
	} else {
		results =
				// Fahrenheit Color Range
			[        
				[value: 31, color: "#153591"],
				[value: 44, color: "#1e9cbb"],
				[value: 59, color: "#90d2a7"],
				[value: 74, color: "#44b621"],
				[value: 84, color: "#f1d801"],
				[value: 95, color: "#d04e00"],
				[value: 96, color: "#bc2323"]
			]  
	}
	return results    
}

void deltaLevelUp() {
	def scale = (state?.scale) ?: getTemperatureScale()
	def currentSetpointDelta = device.currentValue("tempDelta")
	double nextLevel    
	if (scale == 'C') {
		nextLevel=(currentSetpointDelta)? currentSetpointDelta.toDouble():0.0        
		nextLevel = (nextLevel + 0.5).round(1)        
		traceEvent(settings.logFilter,"levelUp>$device.displayName, indiceZone=${state?.indiceZone}, nextLevel=$nextLevel",
			settings.trace,get_LOG_TRACE())        
		if (nextLevel > 10) {
			nextLevel = 10.0
		}
		sendEvent(name:"tempDelta", value: nextLevel, displayed: true)        
	} else {
		nextLevel=(currentSetpointDelta)? currentSetpointDelta.toDouble().round():0        
		nextLevel = (nextLevel + 1)
		traceEvent(settings.logFilter,"levelUp>$device.displayName, indiceZone=${state?.indiceZone}, nextLevel=$nextLevel",
			settings.trace,get_LOG_TRACE())        
		if (nextLevel > 20) {
			nextLevel = 20
		}
		sendEvent(name:"tempDelta", value: nextLevel.intValue(), displayed: true)        
	}
}
void deltaLevelDown() {
	def scale = (state?.scale) ?: getTemperatureScale()
	def currentSetpointDelta = device.currentValue("tempDelta")
	double nextLevel    
	if (scale == 'C') {
		nextLevel=(currentSetpointDelta)? currentSetpointDelta.toDouble():0.0        
		nextLevel = (nextLevel - 0.5).round(1)        
		traceEvent(settings.logFilter,"levelDown>$device.displayName, indiceZone=${state?.indiceZone}, nextLevel=$nextLevel",
			settings.trace,get_LOG_TRACE())        
		if (nextLevel < -10) {
			nextLevel = -10.0
		}
		sendEvent(name:"tempDelta", value: nextLevel, displayed: true)        
	} else {
		nextLevel=(currentSetpointDelta)? currentSetpointDelta.toDouble().round():0        
		nextLevel = (nextLevel - 1)
		traceEvent(settings.logFilter,"levelDown>$device.displayName, indiceZone=${state?.indiceZone}, nextLevel=$nextLevel",
			settings.trace,get_LOG_TRACE())        
		if (nextLevel < -20) {
			nextLevel = -20
		}
		sendEvent(name:"tempDelta", value: nextLevel.intValue(), displayed: true)        
	}
}

void setZoneActive() {
	def indiceZone=state?.indiceZone       
	traceEvent(settings.logFilter,"activeZone>about to make zone active, indiceZone=${indiceZone}",
		settings.trace,get_LOG_TRACE())
        
	sendEvent(name: "activeZone", value: "true", displayed: true,isStateChange:true)
}
void setZoneInactive() {
	def indiceZone=state?.indiceZone       
	traceEvent(settings.logFilter,"inactiveZone>about to make zone inactive, indiceZone=${indiceZone}",
		settings.trace,get_LOG_TRACE())
        
	sendEvent(name: "activeZone", value: "false", displayed: true, isStateChange:true)
}

void on() {
	def indiceZone=state?.indiceZone       
	traceEvent(settings.logFilter,"on>about to turn on all vents in zone, indiceZone=${indiceZone}",
		settings.trace,get_LOG_TRACE())
	parent.open_vents_in_zone(indiceZone)    	
        
	sendEvent(name: "switch", value: "on")
}

void off() {
	def indiceZone=state?.indiceZone      
	traceEvent(settings.logFilter,"on>about to turn off all vents in zone, indiceZone=${indiceZone}",
		settings.trace,get_LOG_TRACE())        
	parent.close_vents_in_zone(indiceZone)    	
	sendEvent(name: "switch", value: "off")
} 

private int get_LOG_ERROR() {return 1}
private int get_LOG_WARN()  {return 2}
private int get_LOG_INFO()  {return 3}
private int get_LOG_DEBUG() {return 4}
private int get_LOG_TRACE() {return 5}

def traceEvent(logFilter,message, displayEvent=false, traceLevel=4, sendMessage=false) {
	int LOG_ERROR= get_LOG_ERROR()
	int LOG_WARN=  get_LOG_WARN()
	int LOG_INFO=  get_LOG_INFO()
	int LOG_DEBUG= get_LOG_DEBUG()
	int LOG_TRACE= get_LOG_TRACE()
	int filterLevel=(logFilter)?logFilter.toInteger():get_LOG_WARN()

	if ((displayEvent) || (sendMessage)) {
		def results = [
			name: "verboseTrace",
			value: message,
			displayed: ((displayEvent)?: false)
		]	

		if ((displayEvent) && (filterLevel >= traceLevel)) {
			switch (traceLevel) {
				case LOG_ERROR:
					log.error "${message}"
				break
				case LOG_WARN:
					log.warn "${message}"
				break
				case LOG_INFO:
					log.info  "${message}"
				break
				case LOG_TRACE:
					log.trace "${message}"
				break
				case LOG_DEBUG:
				default:
					log.debug "${message}"
				break
			}  /* end switch*/              
		} /* end if displayEvent*/
		if (sendMessage) sendEvent (results)
	}
	        
    
}