package hackathon.api

import grails.gorm.transactions.Transactional

@Transactional
class AcquireService {

    def initiate(String handoffId) {
        def status = Status.findByHandoffId(handoffId)

        if(!status){
            throw new Exception("")
        }

        status.handoffStatus = HandoffStatus.INPROGRESS
    }

    def updateStatus(String handoffId, def newStatus) {
        def status = Status.findByHandoffId(handoffId)
        status.handoffStatus = HandoffStatus.valueOf(newStatus)
    }
}
