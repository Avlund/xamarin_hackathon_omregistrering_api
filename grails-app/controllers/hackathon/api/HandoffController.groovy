package hackathon.api

import grails.converters.JSON
import org.springframework.http.HttpStatus

class HandoffController {
	static responseFormats = ['json']

    HandoffService handoffService

    def initiate(String licenseplate) {
        def licenseId = request.JSON?.licenseId

        if (!licenseId) {
            response.status = HttpStatus.BAD_REQUEST.value()
            return
        }

        def handoffId = handoffService.initiate(licenseplate, licenseId)

        response.status = HttpStatus.OK.value()
        def returnObj = ['handoffId': handoffId]
        render returnObj as JSON
    }

    def status(String handoffId) {
        def status = Status.findByHandoffId(handoffId)

        response.status = HttpStatus.OK.value()
        if(status) {
            def returnObj = ['status': status.handoffStatus.name(), 'handoffId': status.handoffId]
            render returnObj as JSON
        }

        render 'NOT FOUND'
    }
}
