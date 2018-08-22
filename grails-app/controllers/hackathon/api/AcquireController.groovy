package hackathon.api

import grails.converters.JSON
import org.springframework.http.HttpStatus

class AcquireController {
	static responseFormats = ['json']

    AcquireService acquireService
	
    def initiate(String licenseplate) {
        def handoffId = request.JSON?.handoffId

        if (!handoffId) {
            response.status = HttpStatus.BAD_REQUEST.value()
            return
        }

        try {
            acquireService.initiate(handoffId)
            def returnObj = "{'licensePlate': '${licenseplate}'}"
            render returnObj
        } catch (Exception e) {
            response.status = HttpStatus.OK.value()
            render 'NOT FOUND'
        }
    }

    def status(String handoffId) {
        def newStatus = request.JSON?.status

        if (!newStatus) {
            response.status = HttpStatus.BAD_REQUEST.value()
            return
        }

        acquireService.updateStatus(handoffId, newStatus)

        response.status = HttpStatus.OK.value()
    }
}
