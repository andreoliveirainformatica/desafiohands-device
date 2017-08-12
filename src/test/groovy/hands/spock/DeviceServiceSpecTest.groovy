package hands.spock

import com.hands.desafio.gateway.InMemoryDeviceGateway
import com.hands.desafio.http.dto.DeviceResponse
import com.hands.desafio.usecase.DeviceService
import com.hands.desafio.usecase.convert.csv.DeviceConverter
import com.hands.desafio.usecase.impl.DeviceServiceImpl
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

import static org.assertj.core.api.Assertions.assertThat

@Unroll
@Narrative('Device Search')
@Title('Device Search')
class DeviceServiceSpecTest extends Specification {

    def deviceGateway = new InMemoryDeviceGateway()
    def deviceService = new DeviceServiceImpl(deviceGateway, new DeviceConverter())

    def "Consultar um Device por UserId"() {
        deviceGateway.clearCache()
        populateCache(deviceService)

        given: "Dado uma consulta de Device por UserId "

        def useId = "813b190e-09d0-4545-80da-ade4405c8901"

        when: "quando for feita a solicitacao de consulta"
        def List<DeviceResponse> deviceResponses  = deviceService.findByUserId(useId)

        then: "será gerado a consulta de uma Lista de DeviceResponse"
        assertThat(deviceResponses.get(0).getUserId()).isEqualTo(useId)
    }

    private void populateCache(DeviceService deviceService) {
        def line =
                "813b190e-09d0-4545-80da-ade4405c8901; " +
                        "ca515e36bda92767; " +
                        "android; " +
                        "6.0; " +
                        "Motorola Moto X (2nd Gen);" +
                        " -46.5933971 -23.5169625;" +
                        " -46.6576926 -23.5597101; " +
                        "Facebook; " +
                        "1; " +
                        "74; " +
                        "11-06-2017 11:06:53; " +
                        "11-06-2017 11:44:09; " +
                        "Playground; " +
                        "Playground - Praça do Pôr do Sol; " +
                        "37; " +
                        "7; " +
                        "-46.703318492148675 " +
                        "-23.55298042202491; " +
                        "R. Des. Ferreira França  1; " +
                        "São Paulo; " +
                        "SP; " +
                        "Brasil";

        deviceService.importDevices(line)
    }
}
