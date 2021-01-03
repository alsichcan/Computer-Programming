#include "CSI.h"

Complex::Complex(): real(0), imag(0) {}

CSI::CSI(): data(nullptr), num_packets(0), num_channel(0), num_subcarrier(0) {}

CSI::~CSI() {
    if(data) {
        for(int i = 0 ; i < num_packets; i++) {
            delete[] data[i];
        }
        delete[] data;
    }
}

int CSI::packet_length() const {
    return num_channel * num_subcarrier;
}

void CSI::print(std::ostream& os) const {
    for (int i = 0; i < num_packets; i++) {
        for (int j = 0; j < packet_length(); j++) {
            os << data[i][j] << ' ';
        }
        os << std::endl;
    }
}

std::ostream& operator<<(std::ostream &os, const Complex &c) {
    return os << c.real << "+" << c.imag << "i";
}

void read_csi(const char* filename, CSI* csi) {
    std::string str;
    int lineIdx = 0;
    // Create ifstream object with filename
    std::ifstream csiFile(filename);

    // Initialize CSI's Data
    getline(csiFile, str);
    csi->num_packets = std::stoi(str);
    getline(csiFile, str);
    csi->num_channel = std::stoi(str);
    getline(csiFile, str);
    csi->num_subcarrier = std::stoi(str);

    csi->data = new Complex*[csi->num_packets];
    for(int i = 0; i < csi->num_packets; i++)
        csi->data[i] = new Complex[csi->num_channel * csi->num_subcarrier];

    // Organizing CSI values to CSI's Data
    for(int packet_idx = 0; packet_idx < csi->num_packets; packet_idx++){
        for(int subcarrier_idx = 0; subcarrier_idx < csi->num_subcarrier; subcarrier_idx++){
            for(int channel_idx = 0; channel_idx < csi->num_channel; channel_idx++){
                Complex val;
                getline(csiFile, str);
                val.real = std::stoi(str);
                getline(csiFile, str);
                val.imag = std::stoi(str);

                csi->data[packet_idx][channel_idx * csi->num_subcarrier + subcarrier_idx] = val;
            }
        }


    }
    csiFile.close();
}

float** decode_csi(CSI* csi) {
    // Initialize Amplitude Data
    float** ampData = new float*[csi->num_packets];
    for(int i = 0; i < csi->num_packets; i++)
        ampData[i] = new float[csi->num_channel * csi->num_subcarrier];

    // Organizing Amplitude values to Amplitude Data
    for (int i = 0; i < csi->num_packets; i++) {
        for (int j = 0; j < csi->packet_length(); j++) {
            Complex val = csi->data[i][j];
            ampData[i][j] = sqrt(val.real * val.real + val.imag * val.imag);
        }
    }

    return ampData;
}

float* get_std(float** decoded_csi, int num_packets, int packet_length) {
    // Initialize Standard Deviation Data
    float* stdData = new float[num_packets];

    // Organize Standard Deviation values to Standard Deviation Data
    for(int packet_idx = 0; packet_idx < num_packets; packet_idx++){
        float std = standard_deviation(decoded_csi[packet_idx], packet_length);
        stdData[packet_idx] = std;
    }
    return stdData;
}

void save_std(float* std_arr, int num_packets, const char* filename) {
    // Create and open a text file
    std::ofstream saveFile(filename);
    // Write to the file
    for(int i = 0; i < num_packets; i++){
        saveFile << std_arr[i] << " ";
    }
    // Close the file
    saveFile.close();
}

// convenience functions
float standard_deviation(float* data, int array_length) {
    float mean = 0, var = 0;
    for (int i = 0; i < array_length; i++) {
        mean += data[i];
    }
    mean /= array_length;
    for (int i = 0; i < array_length; i++) {
        var += pow(data[i]-mean,2);
    }
    var /= array_length;
    return sqrt(var);
}