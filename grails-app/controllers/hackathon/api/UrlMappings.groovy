package hackathon.api

class UrlMappings {

    static mappings = {
        group('/handoff') {
            get "/status/$handoffId"(controller: 'handoff', action: 'status')
            post "/initiate/$licenseplate"(controller: 'handoff', action: 'initiate')
        }

        group('/acquire') {
            post "/initiate/$licenseplate"(controller: 'acquire', action: 'initiate')
            put "/status/$handoffId"(controller: 'acquire', action: 'status')
        }

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
