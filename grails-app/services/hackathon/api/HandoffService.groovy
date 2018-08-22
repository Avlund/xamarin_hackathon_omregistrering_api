package hackathon.api

import grails.gorm.transactions.Transactional

@Transactional
class HandoffService {

    def initiate(String licensePlate, String licenseId) {
        def r = new Random()
        def handoffId = ''
        (0..3).each {
            handoffId += r.nextInt(10)
        }

        new Car(licensePlate: licensePlate, licenseId: licenseId, handoffId: handoffId).save(failOnError: true)

        new Status(handoffId: handoffId, handoffStatus: HandoffStatus.INITIALISED).save(failOnError: true)

        return handoffId
    }
}
